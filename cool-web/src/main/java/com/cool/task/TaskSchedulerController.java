package com.cool.task;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cool.api.TaskGroupService;
import com.cool.api.TaskSchedulerService;
import com.cool.base.BaseController;
import com.cool.model.TaskGroup;
import com.cool.model.TaskScheduler;
import com.cool.util.HtmlUtil;
import com.cool.util.Request2ModelUtil;
import com.cool.util.WebUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("scheduler")
public class TaskSchedulerController extends BaseController{
	private final Logger logger = Logger.getLogger(TaskSchedulerController.class);
	@Autowired
	private TaskSchedulerService taskSchedulerService;
	
	@Autowired
	private TaskGroupService taskGroupService;
	/**
	 * 
	* @Title: list 
	* @Description: 跳转列表页
	* @param @param request
	* @param @param response
	* @param @return     
	* @return Object    
	* @throws
	 */
	@RequiresPermissions("task.scheduler.list")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "任务调度");
		return forword("task/scheduler/scheduler",context);
	}
	/**
	 * 
	* @Title: dataList 
	* @Description: 分页查询
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("task.scheduler.dataList")
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<TaskScheduler> pageList = taskSchedulerService.query(params);
		HtmlUtil.writerJson(response,pageList);
	}
	/**
	 * 
	* @Title: cancel 
	* @Description: 逻辑删除
	* @param @param ids
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("task.scheduler.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		taskSchedulerService.cancelDBAndCache(ids, getCurrUser());
		sendSuccessMessage(response,"删除成功");
	}
	/**
	 * 
	* @Title: start 
	* @Description: 执行任务
	* @param @param id
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("task.scheduler.run")
	@RequestMapping(value="/run",method = RequestMethod.POST)
	public void run(Long id,HttpServletRequest request, HttpServletResponse response) {
		TaskScheduler taskScheduler = taskSchedulerService.queryDBById(id);
		TaskGroup taskGroup = taskGroupService.queryDBById(taskScheduler.getGroupId());
		boolean result = taskSchedulerService.execTask(taskGroup.getGroupName(), taskScheduler.getTaskName());
		if(result) {
			sendSuccessMessage(response,"执行成功");
		}else {
			sendFailureMessage(response,"执行失败");
		}
	}
	
	@RequiresPermissions("task.scheduler.start")
	@RequestMapping(value="/start",method = RequestMethod.POST)
	public void start(Long id,HttpServletRequest request, HttpServletResponse response) {
		TaskScheduler taskScheduler = taskSchedulerService.queryDBById(id);
		TaskGroup taskGroup = taskGroupService.queryDBById(taskScheduler.getGroupId());
		boolean result = taskSchedulerService.openCloseTask(taskGroup.getGroupName(), taskScheduler.getTaskName(), "start");
		if(result) {
			sendSuccessMessage(response,"启动成功");
		}else {
			sendFailureMessage(response,"启动失败");
		}
	}
	
	/**
	 * 
	* @Title: stop 
	* @Description: 停止任务
	* @param @param id
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("task.scheduler.stop")
	@RequestMapping(value="/stop",method = RequestMethod.POST)
	public void stop(Long id,HttpServletRequest request, HttpServletResponse response) {
		TaskScheduler taskScheduler = taskSchedulerService.queryDBById(id);
		TaskGroup taskGroup = taskGroupService.queryDBById(taskScheduler.getGroupId());
		boolean result = taskSchedulerService.openCloseTask(taskGroup.getGroupName(), taskScheduler.getTaskName(), "stop");
		if(result) {
			sendSuccessMessage(response,"停止成功");
		}else {
			sendFailureMessage(response,"停止失败");
		}
	}
	
	/**
	 * 
	* @Title: detete 
	* @Description: 物理删除
	* @param @param ids
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("task.scheduler.delete")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void detete(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		taskSchedulerService.deleteDBAndCache(ids);
		sendSuccessMessage(response,"删除成功");
	}
	
	/**
	 * 
	* @Title: queryById 
	* @Description: 根据id查询
	* @param @param id
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/queryById",method = RequestMethod.POST)
	public void queryById(Long id,HttpServletRequest request, HttpServletResponse response) {
		TaskScheduler record = taskSchedulerService.queryCacheById(id);
		HtmlUtil.writerJson(response,record);
	}
	/**
	 * 
	* @Title: save 
	* @Description: 新增或更新
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		TaskScheduler record = Request2ModelUtil.covert(TaskScheduler.class,request);
		if(record != null) {
			if(record.getId() == null) {
				Map<String,Object> searchParams = Maps.newConcurrentMap();
				searchParams.put("groupId", record.getGroupId());
				searchParams.put("taskName", record.getTaskName());
				List<TaskScheduler> checkList = taskSchedulerService.validateTaskScheduler(searchParams);
				if(checkList != null && checkList.size()>0) {
					sendFailureMessage(response,"新增失败，任务已存在");
					return;
				}
				record.setCreateBy(getCurrUser());
				taskSchedulerService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				taskSchedulerService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
}
