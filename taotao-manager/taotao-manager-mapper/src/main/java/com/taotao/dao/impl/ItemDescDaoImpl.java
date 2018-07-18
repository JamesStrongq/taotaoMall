package com.taotao.dao.impl;

import com.taotao.dao.ItemDescDao;
import com.taotao.pojo.TbItemDesc;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDescDaoImpl implements ItemDescDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        TbItemDesc itemDesc = sessionFactory.openSession().get(TbItemDesc.class,itemId);
        return itemDesc;
    }
}
