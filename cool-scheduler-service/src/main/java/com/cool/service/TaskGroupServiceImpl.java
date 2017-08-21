package com.cool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.TaskGroupService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.TaskGroupMapper;
import com.cool.exception.ServiceException;
import com.cool.model.TaskGroup;
import com.github.pagehelper.PageInfo;

@Service
public class TaskGroupServiceImpl extends BaseServiceImpl<TaskGroup> implements TaskGroupService{
	
	@Autowired
	private TaskGroupMapper taskGroupMapper;

	@Override
	public PageInfo<TaskGroup> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<TaskGroup> getMapper() {
		return taskGroupMapper;
	}

	@Override
	public List<TaskGroup> queryAllGroup(Map<String, Object> params) {
		return taskGroupMapper.queryAllGroup(params);
	}

	@Override
	public List<TaskGroup> validateTaskGroup(Map<String, Object> params) {
		return taskGroupMapper.queryAllGroup(params);
	}

}
