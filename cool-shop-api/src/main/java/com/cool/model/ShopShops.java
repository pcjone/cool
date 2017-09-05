package com.cool.model;

import com.cool.base.BaseModel;

import java.util.Date;
/**
 * 
* @ClassName: ShopShops 
* @Description: 商铺
* @author panlei
* @date 2017年9月1日 下午4:02:52 
*
 */
public class ShopShops extends BaseModel {

	/**
	 * @Fields serialVersionUID : 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商铺名称
	 */
	private String shopName;

	/**
	 * 介绍
	 */
	private String shopIntroduce;
	
	private Long shopImage;

	/**
	 * 类型
	 */

	private Long shopType;

	/**
	 * 用户id
	 */

	private Long userId;

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getShopImage() {
		return shopImage;
	}

	public void setShopImage(Long shopImage) {
		this.shopImage = shopImage;
	}

	public String getShopIntroduce() {
		return this.shopIntroduce;
	}

	public void setShopIntroduce(String shopIntroduce) {
		this.shopIntroduce = shopIntroduce;
	}

	public Long getShopType() {
		return this.shopType;
	}

	public void setShopType(Long shopType) {
		this.shopType = shopType;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}