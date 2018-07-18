package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        TaotaoResult result = cartService.addCartItem(request,response,itemId,num);
        return "redirect:/cart/success.html";

    }

    @RequestMapping("/success")
    public String showSuccess(){
        return "cartSuccess";
    }

    @RequestMapping("/cart")
    public String getCartItem(HttpServletRequest request, HttpServletResponse response, Model model){
        List<CartItem> list = cartService.getCartItemList(request,response);
        model.addAttribute("cartList",list);
        return "cart";
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(HttpServletRequest request,HttpServletResponse response,@PathVariable Long itemId){
            cartService.deleteCartItem(request,response,itemId);
            return "redirect:/cart/cart.html";
    }
}
