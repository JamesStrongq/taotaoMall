package com.taotao.dao.impl;

import com.taotao.dao.ItemParamItemDao;
import com.taotao.pojo.TbItemParamItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemParamItemDaoImpl implements ItemParamItemDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public TbItemParamItem getItemParamItemById(long itemId) {
        TbItemParamItem tbItemParamItem = sessionFactory.getCurrentSession().get(TbItemParamItem.class,itemId);
        return tbItemParamItem;
    }
}
