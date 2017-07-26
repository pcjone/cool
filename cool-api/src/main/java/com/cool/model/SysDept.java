package com.cool.model;

import com.cool.base.BaseModel;
/**
 * 
* @ClassName: SysDept 
* @Description: 部门管理
* @author panlei
* @date 2017年7月19日 上午10:14:15 
*
 */
public class SysDept extends BaseModel{
	
	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = -3444960951493634505L;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 上级部门编号
	 */
	private Long parentId;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 隶属单位
	 */
	private Integer unitId;
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
	private Integer leaf;
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getLeaf() {
		return leaf;
	}
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

}
