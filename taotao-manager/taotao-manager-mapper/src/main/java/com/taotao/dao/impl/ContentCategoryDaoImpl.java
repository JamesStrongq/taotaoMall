package com.taotao.dao.impl;

import com.taotao.dao.ContentCategoryDao;
import com.taotao.pojo.TbContentCategory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentCategoryDaoImpl implements ContentCategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TbContentCategory> getContentCategoryList(long parentId) {
        Criteria criteria = sessionFactory.openSession().createCriteria(TbContentCategory.class);
        criteria.add(Restrictions.eq("parentId",parentId));
        return criteria.list();
    }

    @Override
    public void insertContentCategoyr(TbContentCategory contentCategory) {
        sessionFactory.openSession().save(contentCategory);
    }

    @Override
    public TbContentCategory getContentCategory(long id) {
        TbContentCategory contentCategory = sessionFactory.openSession().get(TbContentCategory.class,id);
        return contentCategory;
    }

    @Override
    public void updateContentCategory(TbContentCategory tbContentCategory) {
        sessionFactory.openSession().update(tbContentCategory);
    }

    @Override
    public void deleteContentCategory(long id) {
        Session session = sessionFactory.openSession();
        TbContentCategory contentCategory = session.get(TbContentCategory.class,id);
        session.delete(contentCategory);

    }
}
