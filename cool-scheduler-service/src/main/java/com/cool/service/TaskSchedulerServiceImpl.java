package com.cool.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cool.api.TaskSchedulerService;
import com.cool.api.manager.SchedulerManager;
import com.cool.base.BaseMapper;
import com.cool.base.BaseServiceImpl;
import com.cool.dao.TaskSchedulerMapper;
import com.cool.exception.ServiceException;
import com.cool.model.TaskScheduler;
import com.cool.model.expand.TaskSchedulerModel;
import com.github.pagehelper.PageInfo;
/**
 * 
* @ClassName: TaskSchedulerServiceImpl 
* @Description: 定时任务服务
* @author panlei
* @date 2017年8月17日 下午4:23:35 
*
 */
@Service
public class TaskSchedulerServiceImpl extends BaseServiceImpl<TaskScheduler> implements TaskSchedulerService{

	@Autowired
	private TaskSchedulerMapper taskSchedulerMapper;
	
	@Autowired
	private SchedulerManager schedulerManager;
	
	@Override
	public PageInfo<TaskScheduler> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<TaskScheduler> getMapper() {
		return taskSchedulerMapper;
	}
	@Transactional
	@Override
	public boolean execTask(String taskGroup, String taskName) {
		TaskSchedulerModel scheduleJob = new TaskSchedulerModel();
		scheduleJob.setGroupName(taskGroup);
		scheduleJob.setTaskName(taskName);
		return schedulerManager.runJob(scheduleJob);
	}
	
	@Override
	public TaskScheduler updateDB(TaskScheduler record) {
		try {
			record.setUpdateTime(new Date());
			if (record.getId() == null) {
				record.setCreateTime(new Date());
				getMapper().insert(record);
			} else {
				getMapper().updateByPrimaryKey(record);
				//更新执行的定时任务
				schedulerManager.reload();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	@Override
	public boolean openCloseTask(String taskGroup, String taskName, String status) {
		TaskSchedulerModel scheduleJob = new TaskSchedulerModel();
		scheduleJob.setGroupName(taskGroup);
		scheduleJob.setTaskName(taskName);
		if(status.equalsIgnoreCase("stop")) {
			return schedulerManager.resumeJob(scheduleJob);
		}else if(status.equalsIgnoreCase("start")){
			return schedulerManager.stopJob(scheduleJob);
		}
		return false;
	}

	@Override
	public List<TaskScheduler> queryTaskSchedulerList(Map<String, Object> params) {
		return taskSchedulerMapper.queryTaskSchedulerList(params);
	}

}
