package com.cool.model;

import com.cool.base.BaseModel;
/**
 * 
* @ClassName: SysRole 
* @Description: 系统角色
* @author panlei
* @date 2017年7月18日 下午6:13:25 
*
 */
public class SysRole extends BaseModel{
	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1619910999962707997L;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 所属部门编号
	 */
	private Long deptId;
	/**
	 * 角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)
	 */
	private Integer roleType;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	
	
}
