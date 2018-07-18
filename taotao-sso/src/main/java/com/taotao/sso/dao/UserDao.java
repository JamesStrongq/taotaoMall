package com.taotao.sso.dao;

import com.taotao.sso.pojo.TbUser;

public interface UserDao {
        boolean judgeUser(String content,String type);
        void insertUser(TbUser user);
        TbUser getUserByName(String username);
}
