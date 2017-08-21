package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.TaskGroupService;
import com.cool.api.TaskRpcService;
import com.cool.model.TaskGroup;
@Service
public class TaskRpcServiceImpl implements TaskRpcService {
	
	@Autowired
	private TaskGroupService taskGroupService;

	@Override
	public List<TaskGroup> queryAllGroup(Map<String, Object> params) {
		return taskGroupService.queryAllGroup(params);
	}

}
