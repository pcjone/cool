package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.TaskSchedulerService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.TaskSchedulerMapper;
import com.cool.exception.ServiceException;
import com.cool.model.TaskScheduler;
import com.github.pagehelper.PageInfo;

@Service
public class TaskSchedulerServiceImpl extends BaseServiceImpl<TaskScheduler> implements TaskSchedulerService{

	@Autowired
	private TaskSchedulerMapper taskSchedulerMapper;
	
	@Override
	public PageInfo<TaskScheduler> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<TaskScheduler> getMapper() {
		return taskSchedulerMapper;
	}

}
