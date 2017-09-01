package com.cool.model;

import com.cool.base.BaseModel;

import java.util.Date;

public class ShopGoods extends BaseModel{

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1L;

	/**
	*商铺id
	*/
	    
	private Long shopId;	
	
	    
	/**
	*商品名称
	*/
	private String goodsName;	
	    
	
	    
	/**
	*商品价格
	*/
	private String goodsDetail;	
	    
	
	    
	/**
	*金额（分）
	*/
	    
	private Long amount;	
	
	    
	/**
	*数量
	*/
	    
	private Long count;	
	
	    
	/**
	*商品图片
	*/
	private String goodsIcon;	
	    
	
	    
	/**
	*商品类型
	*/
	    
	private Long goodsType;	
	
	    
	/**
	*商品状态
	*/
	    
	private Long goodsStatus;	
	
	    
	/**
	*版本号
	*/
	    
	private Long version;	
	
	    
	
	
	public Long getShopId() {
		return this.shopId;
	}
		
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}		
	
	public String getGoodsName() {
		return this.goodsName;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	
	public String getGoodsDetail() {
		return this.goodsDetail;
	}
	
	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
	
	
	
	
	public Long getAmount() {
		return this.amount;
	}
		
	public void setAmount(Long amount) {
		this.amount = amount;
	}		
	
	
	public Long getCount() {
		return this.count;
	}
		
	public void setCount(Long count) {
		this.count = count;
	}		
	
	public String getGoodsIcon() {
		return this.goodsIcon;
	}
	
	public void setGoodsIcon(String goodsIcon) {
		this.goodsIcon = goodsIcon;
	}
	
	
	
	
	public Long getGoodsType() {
		return this.goodsType;
	}
		
	public void setGoodsType(Long goodsType) {
		this.goodsType = goodsType;
	}		
	
	
	public Long getGoodsStatus() {
		return this.goodsStatus;
	}
		
	public void setGoodsStatus(Long goodsStatus) {
		this.goodsStatus = goodsStatus;
	}		
	
	
	public Long getVersion() {
		return this.version;
	}
		
	public void setVersion(Long version) {
		this.version = version;
	}		
	

}