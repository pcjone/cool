package com.cool.model;

import com.cool.base.BaseModel;

/**
 * 
* @ClassName: Group 
* @Description: 分组任务
* @author panlei
* @date 2017年8月10日 上午10:56:52 
*
 */
public class TaskGroup extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 分组名称
	 */
	private String groupName;
	/**
	 * 分组描述
	 */
	private String groupDesc;
	/**
	 * 分组状态，0禁用，1启用
	 */
	private Integer status;

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	/**
	 * @return the description
	 */
	public String getGroupDesc() {
		return this.groupDesc;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return this.status;
	}

}
