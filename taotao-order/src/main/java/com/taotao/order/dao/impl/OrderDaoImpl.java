package com.taotao.order.dao.impl;

import com.taotao.order.dao.OrderDao;
import com.taotao.order.pojo.TbOrder;
import com.taotao.order.pojo.TbOrderItem;
import com.taotao.order.pojo.TbOrderShipping;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insertTbOrder(TbOrder order) {
        sessionFactory.openSession().save(order);
    }

    @Override
    public void insertTbOrderItem(TbOrderItem orderItem) {
        sessionFactory.openSession().save(orderItem);
    }

    @Override
    public void insertTbOrderShipping(TbOrderShipping orderShipping) {
        sessionFactory.openSession().save(orderShipping);
    }
}
