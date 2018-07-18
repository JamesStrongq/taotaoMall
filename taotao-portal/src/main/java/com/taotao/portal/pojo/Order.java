package com.taotao.portal.pojo;

import java.util.List;

public class Order extends TbOrder {
    private String payment;
    private String postFee;
    private Long userId;
    private String buyerMessage;
    private String buyerNick;

    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }

    @Override
    public String getPayment() {
        return payment;
    }

    @Override
    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String getPostFee() {
        return postFee;
    }

    @Override
    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getBuyerMessage() {
        return buyerMessage;
    }

    @Override
    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    @Override
    public String getBuyerNick() {
        return buyerNick;
    }

    @Override
    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    @Override
    public String toString() {
        return "Order{" +
                "payment='" + payment + '\'' +
                ", postFee='" + postFee + '\'' +
                ", userId=" + userId +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", buyerNick='" + buyerNick + '\'' +
                ", orderItems=" + orderItems +
                ", orderShipping=" + orderShipping +
                '}';
    }
}
