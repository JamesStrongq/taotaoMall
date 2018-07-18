package com.taotao.portal.interceptor;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.portal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在handler执行之前处理
        //1、判断用户是否登录，从cookie中取tooken
        String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
        //根据token换取用户信息,调用sso系统的接口
        TbUser user = userService.getUserByToken(token);
        //取不到用户信息，跳转到登录界面，把用户请求的url作为参数传递给登录页面，传递完之后返回false
        if(null == user){
            response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_PAGE_LOGIN + "?redirect="
                    + request.getRequestURI());
            return false;
        }
        //取到用户信息，放行
        //把用户信息放入request中
        request.setAttribute("user",user);
        //返回值决定handler是否执行，true执行，false不执行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //handler执行之后，返回ModleAndView之前执行

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //返回ModelAndView之后执行
        //相应用户之后执行


    }
}
