package com.cool.model;

import com.cool.base.BaseModel;

/**
 * 
* @ClassName: SysUserRole 
* @Description: 用户和角色关系
* @author panlei
* @date 2017年7月18日 下午6:16:09 
*
 */
public class SysUserRole extends BaseModel{
	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = -1171563336731119217L;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 角色id
	 */
	private Long roleId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
