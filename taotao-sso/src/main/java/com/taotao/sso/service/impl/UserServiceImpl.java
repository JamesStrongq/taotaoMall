package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.dao.UserDao;
import com.taotao.sso.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Override
    public TaotaoResult checkData(String content, Integer type) {
        boolean boo = false;

        if (1 == type){
            boo = userDao.judgeUser(content,"username");
        }else if(2 == type){
            boo = userDao.judgeUser(content,"phone");
        }else{
            boo = userDao.judgeUser(content,"email");
        }
        if(boo == true){
            return TaotaoResult.ok(false);
        }
        return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult createUser(TbUser user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userDao.insertUser(user);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult loginUser(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        TbUser user = userDao.getUserByName(username);
        if(user == null){
            return TaotaoResult.build(400,"用户名或密码错误");
        }
        //对比密码
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            return TaotaoResult.build(400,"用户名或密码错误");
        }
        //生成token
        String token = UUID.randomUUID().toString();
        //保存用户之前，把用户中的密码清掉
        user.setPassword(null);
        //把用户信息写入redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        //设置session的过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,SSO_SESSION_EXPIRE);

        //添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效
        CookieUtils.setCookie(request,response,"TT_TOKEN",token);

        //返回token
        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {

        //根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        if(StringUtils.isBlank(json)){
           TaotaoResult.build(400,"此session已经过期，请重新登录");
        }
        //更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,SSO_SESSION_EXPIRE);

        return TaotaoResult.ok(JsonUtils.jsonToPojo(json,TbUser.class));
    }
}
