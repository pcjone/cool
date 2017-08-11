package com.cool.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cool.base.BaseModel;
/**
 * 
* @ClassName: Scheduler 
* @Description: 定时任务
* @author panlei
* @date 2017年8月10日 上午10:56:15 
*
 */
public class TaskScheduler extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 所属分组ID
	 */
	private Long groupId;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 任务描述
	 */
	private String taskDesc;
	/**
	 * 任务类型
	 */
	private String taskType;
	/**
	 * 时间策略
	 */
	private String timeConfig;
	/**
	 * 状态，0:停用;1:启用
	 */
	private Integer status;
	/**
	 * 任务最后执行时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date taskLastExecTime;
	/**
	 * 任务最近一次执行时长
	 */
	private Long taskLastExecTimes;
	/**
	 * 任务下次执行时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date taskNextExecTime;
	/**
	 * 生效时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date effectTime;
	/**
	 * 自动随系统启动
	 */
	private Integer autoStart;

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return this.groupId;
	}

	/**
	 * @param taskName
	 *            the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return this.taskName;
	}

	/**
	 * @param taskDesc
	 *            the taskDesc to set
	 */
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	/**
	 * @return the taskDesc
	 */
	public String getTaskDesc() {
		return this.taskDesc;
	}

	/**
	 * @param taskType
	 *            the taskType to set
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return the taskType
	 */
	public String getTaskType() {
		return this.taskType;
	}

	/**
	 * @param timeConfig
	 *            the timeConfig to set
	 */
	public void setTimeConfig(String timeConfig) {
		this.timeConfig = timeConfig;
	}

	/**
	 * @return the timeConfig
	 */
	public String getTimeConfig() {
		return this.timeConfig;
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

	/**
	 * @param taskLastExecTime
	 *            the taskLastExecTime to set
	 */
	public void setTaskLastExecTime(Date taskLastExecTime) {
		this.taskLastExecTime = taskLastExecTime;
	}

	/**
	 * @return the taskLastExecTime
	 */
	public Date getTaskLastExecTime() {
		return this.taskLastExecTime;
	}

	/**
	 * @param taskLastExecTimes
	 *            the taskLastExecTimes to set
	 */
	public void setTaskLastExecTimes(Long taskLastExecTimes) {
		this.taskLastExecTimes = taskLastExecTimes;
	}

	/**
	 * @return the taskLastExecTimes
	 */
	public Long getTaskLastExecTimes() {
		return this.taskLastExecTimes;
	}

	/**
	 * @param taskNextExecTime
	 *            the taskNextExecTime to set
	 */
	public void setTaskNextExecTime(Date taskNextExecTime) {
		this.taskNextExecTime = taskNextExecTime;
	}

	/**
	 * @return the taskNextExecTime
	 */
	public Date getTaskNextExecTime() {
		return this.taskNextExecTime;
	}

	/**
	 * @param effectTime
	 *            the effectTime to set
	 */
	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	/**
	 * @return the effectTime
	 */
	public Date getEffectTime() {
		return this.effectTime;
	}

	/**
	 * @return property value of autoStart
	 */
	public Integer getAutoStart() {
		return autoStart;
	}

	/**
	 * @param autoStart value to be assigned to property autoStart
	 */
	public void setAutoStart(Integer autoStart) {
		this.autoStart = autoStart;
	}

}
