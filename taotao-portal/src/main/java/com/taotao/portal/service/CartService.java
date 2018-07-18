package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {
    TaotaoResult addCartItem(HttpServletRequest request, HttpServletResponse response, long itemId, int num);
    List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response);
    TaotaoResult deleteCartItem(HttpServletRequest request, HttpServletResponse response, long itemId);
}
