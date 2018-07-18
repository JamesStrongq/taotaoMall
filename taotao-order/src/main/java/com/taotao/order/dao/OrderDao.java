package com.taotao.order.dao;

import com.taotao.order.pojo.TbOrder;
import com.taotao.order.pojo.TbOrderItem;
import com.taotao.order.pojo.TbOrderShipping;

public interface OrderDao {
    void insertTbOrder(TbOrder order);
    void insertTbOrderItem(TbOrderItem orderItem);
    void insertTbOrderShipping(TbOrderShipping orderShipping);
}
