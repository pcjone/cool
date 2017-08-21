package com.cool.api.manager;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Trigger;

import com.cool.model.expand.TaskSchedulerModel;

public interface SchedulerManager {
	/**获取所有的job*/
	public List<TaskSchedulerModel> getAllJobDetail();
	
	public JobDetail getJobDetailByTriggerName(Trigger trigger);
	/** 获取运行中的job */
	public List<TaskSchedulerModel> getRunningJobDetail();
	
	/** 暂停任务 */
	public boolean stopJob(TaskSchedulerModel scheduleJob);
	
	/** 恢复任务 */
	public boolean resumeJob(TaskSchedulerModel scheduleJob);

	/** 运行任务 */
	public boolean runJob(TaskSchedulerModel scheduleJob);

	/** 刷新调度(新增任务为暂停状态) */
	public boolean refreshScheduler();

	public void reload() throws Exception;
}
