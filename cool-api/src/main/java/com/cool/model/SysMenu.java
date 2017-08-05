package com.cool.model;

import java.util.List;

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
	private static final long serialVersionUID = 1L;
	
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单类型(0:CURD;1:系统菜单;)
	 */
	private Long menuType;
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
	private Long expand;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
	private Long isShow;
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
	public Long getMenuType() {
		return menuType;
	}
	public void setMenuType(Long menuType) {
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
	public Long getExpand() {
		return expand;
	}
	public void setExpand(Long expand) {
		this.expand = expand;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Long getIsShow() {
		return isShow;
	}
	public void setIsShow(Long isShow) {
		this.isShow = isShow;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}

}
