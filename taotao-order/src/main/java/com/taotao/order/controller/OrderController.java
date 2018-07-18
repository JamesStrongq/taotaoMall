package com.taotao.order.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.Order;
import com.taotao.order.pojo.TbOrder;
import com.taotao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create",method= RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody Order order){
        TbOrder tbOrder = new TbOrder();
        tbOrder.setPayment(order.getPayment());
        tbOrder.setPostFee(order.getPostFee());
        tbOrder.setUserId(order.getUserId());
        tbOrder.setBuyerMessage(order.getBuyerMessage());
        tbOrder.setBuyerNick(order.getBuyerNick());
        TaotaoResult result = orderService.createOrder(tbOrder,order.getOrderItems(),order.getOrderShipping());
        return result;
    }
}
