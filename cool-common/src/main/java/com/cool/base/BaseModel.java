package com.cool.base;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class BaseModel implements Serializable {

	/**  */
	protected static final long serialVersionUID = -6029899615911539532L;
	/**
	 * ID
	 */
	protected Long id;
	/**
	 * 是否锁定,0为正常，1为锁定
	 */
	protected Integer enable;
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date createTime;
	/**
	 * 创建人
	 */
	protected String createBy;
	/**
	 * 修改时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date updateTime;
	/**
	 * 修改人
	 */
	protected String updateBy;
	/**
	 * 备注
	 */
	protected String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
