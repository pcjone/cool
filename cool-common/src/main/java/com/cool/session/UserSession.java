package com.cool.session;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: UserSession 
* @Description: 用户属性的信息存储
* @author panlei
* @date 2017年7月21日 下午1:52:27 
*
 */
public class UserSession implements Serializable {
	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 7542738449824934875L;
	
	private Long id;
	
	private String account;
	
	private String userType;
	
	private Long shopId;
	/**
	 * 用户权限
	 */
	private List<String> permissionList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<String> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	
}
