package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.TaskScheduler;

public interface TaskSchedulerService extends BaseService<TaskScheduler>{
	
	public List<TaskScheduler> queryTaskSchedulerList(Map<String, Object> params);
	
	/** 执行任务 */
	public boolean execTask(String taskGroup, String taskName);

	/** 启停 */
	public boolean openCloseTask(String taskGroup, String taskName, String status);
}
