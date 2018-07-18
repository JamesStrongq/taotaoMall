package com.taotao.search.pojo;
// Generated 2018-2-2 22:22:29 by Hibernate Tools 4.0.0.Final

import java.util.Date;

/**
 * TbItemDesc generated by hbm2java
 */
public class TbItemDesc implements java.io.Serializable {

	private long itemId;
	private String itemDesc;
	private Date created;
	private Date updated;

	public TbItemDesc() {
	}

	public TbItemDesc(long itemId) {
		this.itemId = itemId;
	}

	public TbItemDesc(long itemId, String itemDesc, Date created, Date updated) {
		this.itemId = itemId;
		this.itemDesc = itemDesc;
		this.created = created;
		this.updated = updated;
	}

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemDesc() {
		return this.itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
