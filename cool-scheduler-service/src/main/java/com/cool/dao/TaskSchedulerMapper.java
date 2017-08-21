package com.cool.dao;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseMapper;
import com.cool.model.TaskScheduler;

public interface TaskSchedulerMapper extends BaseMapper<TaskScheduler>{
	public List<TaskScheduler> queryTaskSchedulerList(Map<String, Object> params);
}
