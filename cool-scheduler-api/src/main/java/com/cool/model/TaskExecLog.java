package com.cool.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cool.base.BaseModel;
/**
 * 
* @ClassName: ExecLog 
* @Description: 调度日志
* @author panlei
* @date 2017年8月10日 上午10:57:03 
*
 */
public class TaskExecLog extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 分组名称
	 */
	private String groupName;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 任务开始时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	/**
	 * 任务结束时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 耗时（秒）
	 */
	private Long exexTime;
	/**
	 * 日志信息
	 */
	private String resultMsg;
	/**
	 * 执行结果，成功；失败；中止；进行中
	 */
	private String resultCode;
	/**
	 * 执行类型，定时触发；人工触发
	 */
	private String triggerType;
	/**
	 * 服务器名
	 */
	private String serverHost;
	/**
	 * 服务器网卡序列号
	 */
	private String serverDuid;
	/**
	 * 服务器IP
	 */
	private String serverIp;

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
	 * @param beginTime
	 *            the beginTime to set
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the beginTime
	 */
	public Date getBeginTime() {
		return this.beginTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * @param exexTime
	 *            the exexTime to set
	 */
	public void setExexTime(Long exexTime) {
		this.exexTime = exexTime;
	}

	/**
	 * @return the exexTime
	 */
	public Long getExexTime() {
		return this.exexTime;
	}

	/**
	 * @param resultMsg
	 *            the resultMsg to set
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	/**
	 * @return the resultMsg
	 */
	public String getResultMsg() {
		return this.resultMsg;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return this.resultCode;
	}

	/**
	 * @param triggerType
	 *            the triggerType to set
	 */
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	/**
	 * @return the triggerType
	 */
	public String getTriggerType() {
		return this.triggerType;
	}

	/**
	 * @param serverHost
	 *            the serverHost to set
	 */
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	/**
	 * @return the serverHost
	 */
	public String getServerHost() {
		return this.serverHost;
	}

	/**
	 * @param serverDuid
	 *            the serverDuid to set
	 */
	public void setServerDuid(String serverDuid) {
		this.serverDuid = serverDuid;
	}

	/**
	 * @return the serverDuid
	 */
	public String getServerDuid() {
		return this.serverDuid;
	}

	/**
	 * @param serverIp
	 *            the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return this.serverIp;
	}

}
