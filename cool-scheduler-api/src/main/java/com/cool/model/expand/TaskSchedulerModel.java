package com.cool.model.expand;

import com.cool.model.TaskScheduler;

public class TaskSchedulerModel extends TaskScheduler{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	//任务组
	private String groupName;
	//状态码
	private String resultCode;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
