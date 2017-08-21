package com.cool.api;

import java.util.List;
import java.util.Map;

import com.cool.model.TaskGroup;

public interface TaskRpcService {
	List<TaskGroup> queryAllGroup(Map<String,Object> params);
}
