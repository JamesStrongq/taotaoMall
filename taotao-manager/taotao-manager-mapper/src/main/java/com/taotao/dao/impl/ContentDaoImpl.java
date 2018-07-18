package com.taotao.dao.impl;

import com.taotao.dao.ContentDao;
import com.taotao.pojo.TbContent;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentDaoImpl implements ContentDao{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<TbContent> getContentList(int firstResult,int maxResults) {
        Criteria criteria = sessionFactory.openSession().createCriteria(TbContent.class);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long findContentCount() {
        Criteria criteria = sessionFactory.openSession().createCriteria(TbContent.class);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    @Override
    public List<TbContent> getContentById(long contentCid) {
        Criteria criteria = sessionFactory.openSession().createCriteria(TbContent.class);
        criteria.add(Restrictions.eq("categoryId",contentCid));
        List<TbContent> list = criteria.list();
        return list;
    }

    @Override
    public void insertContent(TbContent tbContent) {
        sessionFactory.openSession().save(tbContent);
    }

    @Override
    public List<TbContent> getContentList() {
        Criteria criteria = sessionFactory.openSession().createCriteria(TbContent.class);
        return criteria.list();
    }
}
