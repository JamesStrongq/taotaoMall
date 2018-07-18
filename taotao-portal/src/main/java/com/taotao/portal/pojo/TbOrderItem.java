package com.taotao.portal.pojo;
// Generated 2018-2-2 22:22:29 by Hibernate Tools 4.0.0.Final

/**
 * TbOrderItem generated by hbm2java
 */
public class TbOrderItem implements java.io.Serializable {

	private String id;
	private String itemId;
	private String orderId;
	private Integer num;
	private String title;
	private Long price;
	private Long totalFee;
	private String picPath;

	public TbOrderItem() {
	}

	public TbOrderItem(String id, String itemId, String orderId) {
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
	}

	public TbOrderItem(String id, String itemId, String orderId, Integer num, String title, Long price, Long totalFee,
			String picPath) {
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.num = num;
		this.title = title;
		this.price = price;
		this.totalFee = totalFee;
		this.picPath = picPath;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}
