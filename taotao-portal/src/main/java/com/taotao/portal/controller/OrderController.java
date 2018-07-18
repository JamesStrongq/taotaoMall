package com.taotao.portal.controller;

import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;
import com.taotao.portal.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model){
        List<CartItem> list = cartService.getCartItemList(request,response);
        model.addAttribute("cartList",list);
        return "order-cart";
    }

    @RequestMapping("/create")
    public String createOrder(Order order, Model model,HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        String orderId = orderService.createOrder(order);
        model.addAttribute("orderId",orderId);
        model.addAttribute("payment",order.getPayment());
        model.addAttribute("date",new DateTime().plusDays(3).toString("yyyy-MM-dd"));
        return "success";
    }
}
