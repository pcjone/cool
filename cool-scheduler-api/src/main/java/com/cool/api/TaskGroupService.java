package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseService;
import com.cool.model.TaskGroup;

public interface TaskGroupService extends BaseService<TaskGroup>{
	public List<TaskGroup> queryAllGroup(Map<String,Object> params);
	
	public List<TaskGroup> validateTaskGroup(Map<String,Object> params);
}
