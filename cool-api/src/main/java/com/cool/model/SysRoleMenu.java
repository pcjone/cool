package com.cool.model;

import com.cool.base.BaseModel;

/**
 * 
* @ClassName: SysRoleMenu 
* @Description: 角色和菜单关系
* @author panlei
* @date 2017年7月18日 下午6:19:00 
*
 */
public class SysRoleMenu extends BaseModel{
	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = -1163960805072595926L;
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 菜单id
	 */
	private Long menuId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
}
