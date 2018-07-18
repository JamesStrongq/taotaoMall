package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Value("${REST_URL}")
    private String REST_URL;

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    @Override
    public TaotaoResult addCartItem(HttpServletRequest request, HttpServletResponse response,long itemId, int num) {
        //取商品信息
        CartItem cartItem = null;
        //取购物车商品列表
        List<CartItem> list = getCartItemList(request);
        //判断购物车商品列表中是否存在此商品
        for(CartItem cItem : list){
            //如果存在此商品
            if(cItem.getId() == itemId){
                cItem.setNum(cItem.getNum() + num);
                cartItem = cItem;
                break;
            }
        }

        if(cartItem == null) {
            cartItem = new CartItem();
            //根据商品id查询商品信息
            String json = HttpClientUtil.doGet(REST_URL + ITEM_INFO_URL + itemId);
            //把json转换成java对象
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
            if (result.getStatus() == 200) {
                TbItem item = (TbItem) result.getData();
                cartItem.setId(item.getId());
                cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
                cartItem.setPrice(item.getPrice());
                cartItem.setTitle(item.getTitle());
                cartItem.setNum(num);
            }
            list.add(cartItem);
        }
        //把购物车列表写入cookie
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(list),true);

        return TaotaoResult.ok();
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> list = getCartItemList(request);
        return list;
    }

    @Override
    public TaotaoResult deleteCartItem(HttpServletRequest request,HttpServletResponse response, long itemId) {
        List<CartItem> itemList = getCartItemList(request);
        //从列表中找到此商品
        for(CartItem item : itemList){
            if(item.getId() == itemId){
                itemList.remove(item);
                break;
            }
        }
        //把购物车列表重新写入cookie
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(itemList),true);

        return TaotaoResult.ok();
    }


    private List<CartItem> getCartItemList(HttpServletRequest request){
        //从cookie中取商品列表
        String cartJson = CookieUtils.getCookieValue(request,"TT_CART",true);

        if(cartJson == null){
            return new ArrayList<>();
        }

        //把json转换成商品列表
        try {

            List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
