package com.taotao.dao.impl;

import com.taotao.dao.ItemDao;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TbItem getItemById(long id) {
        TbItem tbItem = sessionFactory.openSession().get(TbItem.class,id);
        System.out.print("item" + tbItem);
        return tbItem;
    }

    @Override
    public List<TbItem> getItemList(int firstResult, int maxResults) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TbItem.class);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long findCount() {
        Criteria criteria = sessionFactory.openSession().createCriteria(TbItem.class);
        criteria.setProjection(Projections.rowCount());
        System.out.println("总数为" + (Long)criteria.uniqueResult());
        return (Long) criteria.uniqueResult();
    }

    @Override
    public void insertItem(TbItem tbItem) {
        sessionFactory.getCurrentSession().save(tbItem);
    }

    @Override
    public void insertItemDesc(TbItemDesc tbItemDesc) {
        sessionFactory.getCurrentSession().save(tbItemDesc);
    }

    @Override
    public void insertItemParamItem(TbItemParamItem tbItemParam) {
        sessionFactory.getCurrentSession().save(tbItemParam);
    }

}
