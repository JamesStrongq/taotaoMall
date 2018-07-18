package com.taotao.dao.impl;

import com.taotao.dao.ItemParamDao;
import com.taotao.pojo.TbItemParam;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemParamDaoImpl implements ItemParamDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbItemParam> getItemParamById(long id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TbItemParam.class);
        criteria.add(Restrictions.eq("cid",id));
        return criteria.list();
    }

    @Override
    public void insertItemParam(TbItemParam tbItemParam) {
        sessionFactory.getCurrentSession().save(tbItemParam);
    }
}
