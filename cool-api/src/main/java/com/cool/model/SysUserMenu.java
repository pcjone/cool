package com.cool.model;

import com.cool.base.BaseModel;

/**
 * 
* @ClassName: SysUserMenu 
* @Description: 用户和菜单关系
* @author panlei
* @date 2017年7月18日 下午6:16:09 
*
 */
public class SysUserMenu extends BaseModel{
	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = -1171563336731119217L;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 菜单id
	 */
	private Long menuId;
	/**
	 * 菜单权限
	 */
	private String permission;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
