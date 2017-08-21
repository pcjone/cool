package com.cool.service.manager;

import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.cool.Constants;
import com.cool.ConstantsEnum;
import com.cool.api.TaskGroupService;
import com.cool.api.TaskSchedulerService;
import com.cool.model.TaskGroup;
import com.cool.model.TaskScheduler;
import com.cool.util.InstanceUtil;

/**
 * 
* @ClassName: TriggerLoader 
* @Description: 作业加载器
* @author panlei
* @date 2017年8月16日 下午2:26:11 
*
 */
public class TriggerLoader {
	
	@Autowired
	private TaskSchedulerService taskSchedulerService;
	
	@Autowired
	private TaskGroupService taskGroupService;
	
	private String taskType; // 作业类型
	private Class<? extends Job> jobClass; // 执行作业的类
	
	public TriggerLoader(String taskType, Class<? extends Job> jobClass) {
		this.taskType = taskType;
		this.jobClass = jobClass;
	}
	
	public Map<Trigger, JobDetail> loadTriggers() {
		Map<String, Object> params = InstanceUtil.newHashMap();
		params.put("taskType", taskType);
		params.put("enable", Constants.ENABLE_NO);
		List<TaskScheduler> schedulerList = taskSchedulerService.queryTaskSchedulerList(params);
		Map<Trigger, JobDetail> resultMap = InstanceUtil.newHashMap();
		for(TaskScheduler scheduler : schedulerList) {
			TaskGroup group = taskGroupService.queryDBById(scheduler.getGroupId());
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("id", scheduler.getId());
			jobDataMap.put("enable", ConstantsEnum.YesOrNo.YES.key == scheduler.getStatus() ? true
					: false);
			jobDataMap.put("desc", group.getGroupDesc() + ":" + scheduler.getTaskDesc());
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					.withIdentity(scheduler.getTaskName(), group.getGroupName())
					.withDescription(scheduler.getTaskDesc()).storeDurably(true)
					.usingJobData(jobDataMap).build();

			Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(CronScheduleBuilder.cronSchedule(scheduler.getTimeConfig()))
					.withIdentity(scheduler.getTaskName(), group.getGroupName())
					.withDescription(group.getGroupDesc()).forJob(jobDetail)
					.usingJobData(jobDataMap).build();

			resultMap.put(trigger, jobDetail);
		}
		return resultMap;
	}
}
