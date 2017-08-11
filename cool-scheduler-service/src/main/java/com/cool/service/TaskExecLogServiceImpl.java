package com.cool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.api.TaskExecLogService;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.TaskExecLogMapper;
import com.cool.exception.ServiceException;
import com.cool.model.TaskExecLog;
import com.github.pagehelper.PageInfo;
@Service
public class TaskExecLogServiceImpl extends BaseServiceImpl<TaskExecLog> implements TaskExecLogService{
	
	@Autowired
	private TaskExecLogMapper taskExecLogMapper;

	@Override
	public PageInfo<TaskExecLog> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<TaskExecLog> getMapper() {
		return taskExecLogMapper;
	}

}
