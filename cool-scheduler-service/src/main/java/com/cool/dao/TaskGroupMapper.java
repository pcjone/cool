package com.cool.dao;

import java.util.List;
import java.util.Map;

import com.cool.base.BaseMapper;
import com.cool.model.TaskGroup;

public interface TaskGroupMapper extends BaseMapper<TaskGroup>{
	public List<TaskGroup> queryAllGroup(Map<String, Object> params);
}
