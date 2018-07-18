package com.taotao.rest.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.ContentDao;
import com.taotao.pojo.TbContent;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;


    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(long contentCid) {
        //从缓存中取内容
       /* try{
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY,contentCid + "");
            if(!StringUtils.isBlank(result)){
                List<TbContent> listResult = JsonUtils.jsonToList(result,TbContent.class);
                return listResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    */
        List<TbContent> list = contentDao.getContentById(contentCid);
        System.out.println("从数据库中取出的数据为:" + list);

        //向缓存中放内容
        try{
            //把list转换成字符串
            String cacheString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCid + "",cacheString);
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
