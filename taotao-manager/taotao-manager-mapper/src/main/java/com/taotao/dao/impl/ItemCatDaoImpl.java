package com.taotao.dao.impl;

import com.taotao.dao.ItemCatDao;
import com.taotao.pojo.TbItemCat;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemCatDaoImpl implements ItemCatDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbItemCat> getItemCatListById(long parentId) {
        Criteria criteria = sessionFactory.openSession().createCriteria(TbItemCat.class,"parentId");
        return criteria.list();
    }
}
