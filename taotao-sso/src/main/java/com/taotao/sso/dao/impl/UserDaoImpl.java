package com.taotao.sso.dao.impl;

import com.taotao.sso.dao.UserDao;
import com.taotao.sso.pojo.TbUser;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean judgeUser(String content,String type) {
        Criteria criteria = sessionFactory.openSession().createCriteria(TbUser.class);
        criteria.add(Restrictions.eq(type,content));
        List<TbUser> list = criteria.list();
        System.out.println(list);
        if (list != null && list.size() > 0){
            return true;
        }

        return false;
    }

    @Override
    public void insertUser(TbUser user) {
        sessionFactory.openSession().save(user);
    }

    @Override
    public TbUser getUserByName(String username) {
        //TbUser user = sessionFactory.openSession().get(TbUser.class,username);
        Criteria criteria = sessionFactory.openSession().createCriteria(TbUser.class);
        criteria.add(Restrictions.eq("username",username));
        List<TbUser> list = criteria.list();
        if(list != null){
            TbUser user = list.get(0);
            System.out.println("user : " + user);
            return user;
        }
        return null;
    }
}
