package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.sso.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    TaotaoResult checkData(String content,Integer type);
    TaotaoResult createUser(TbUser user);
    TaotaoResult loginUser(String username, String password, HttpServletRequest request,HttpServletResponse response);
    TaotaoResult getUserByToken(String token);
}
