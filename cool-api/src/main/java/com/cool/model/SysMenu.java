package com.cool.model;

import com.cool.base.BaseModel;
/**
 * 
* @ClassName: SysMenu 
* @Description: 系统菜单
* @author panlei
* @date 2017年7月18日 下午6:05:37 
*
 */
public class SysMenu extends BaseModel{

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = -4922695040599977515L;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单类型(0:CURD;1:系统菜单;2:业务菜单;)
	 */
	private Boolean menuType;
	/**
	 * 上级菜单编号
	 */
	private Long parentId;
	/**
	 * 节点图标CSS类名
	 */
	private String iconcls;
	/**
	 * 请求地址
	 */
	private String request;
	/**
	 * 展开状态(1:展开;0:收缩)
	 */
	private Boolean expand;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
	private Boolean isShow;
	/**
	 * 权限标识
	 */
	private String permission;
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Boolean getMenuType() {
		return menuType;
	}
	public void setMenuType(Boolean menuType) {
		this.menuType = menuType;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getIconcls() {
		return iconcls;
	}
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public Boolean getExpand() {
		return expand;
	}
	public void setExpand(Boolean expand) {
		this.expand = expand;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Boolean getIsShow() {
		return isShow;
	}
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
