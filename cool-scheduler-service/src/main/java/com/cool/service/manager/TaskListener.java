package com.cool.service.manager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cool.api.TaskExecLogService;
import com.cool.api.TaskSchedulerService;
import com.cool.model.TaskExecLog;
import com.cool.model.TaskScheduler;
import com.cool.model.enums.TriggerTypeEnum;
import com.cool.task.TaskConstants;
import com.cool.util.NativeUtil;
import com.cool.util.StringUtil;

/**
 * 
* @ClassName: TaskListener 
* @Description: 调度监听器
* @author panlei
* @date 2017年8月16日 下午1:54:32 
*
 */
public class TaskListener implements JobListener{
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private TaskExecLogService taskExexLogService;
	
	@Autowired
	private TaskSchedulerService taskSchedulerService;
	
	// 线程池
	private ExecutorService executorService = Executors.newCachedThreadPool();
	
	@Override
	public String getName() {
		return "taskListener";
	}
	/**
	* (1)
	* 任务执行之前执行
	* Called by the Scheduler when a JobDetail is about to be executed (an associated Trigger has occurred).
	*/
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		JobKey jobKey = context.getJobDetail().getKey();
		String groupName = jobKey.getGroup();
		String jobName = jobKey.getName();
		String isExce = null;
		final TaskExecLog log = new TaskExecLog();
		try {
			isExce = (String) context.getScheduler().getContext().get(jobName + ":" + groupName);
			if (logger.isInfoEnabled()) {
				logger.info("Listened job : " + groupName + "." + jobName + " prepared to start.");
			}
			// 保存日志
			log.setBeginTime(context.getFireTime());
			log.setGroupName(groupName);
			log.setTaskName(jobName);
			log.setResultCode(TaskConstants.INIT_STATS);
			log.setServerHost(NativeUtil.getHostName());
			log.setServerDuid(NativeUtil.getDUID());
			log.setServerIp(NativeUtil.getHostAddress());
			if (isExce != null && isExce.equals("1")) {
				log.setTriggerType(TriggerTypeEnum.TRIGGERTYPE_HAND.value);
			} else {
				log.setTriggerType(TriggerTypeEnum.TRIGGERTYPE_AUTO.value);
			}
			context.getScheduler().getContext().put(jobName + ":" + groupName, null);
		}catch(SchedulerException e) {
			logger.error(e.getMessage());
		}
		//更新日志
		taskExexLogService.updateDB(log);
		jobDataMap.put(TaskConstants.JOB_LOG, log);
	}
	/**
	* (2)
	* 这个方法正常情况下不执行,但是如果当TriggerListener中的vetoJobExecution方法返回true时,那么执行这个方法.
	* 需要注意的是 如果方法(2)执行 那么(1),(3)这个俩个方法不会执行,因为任务被终止了嘛.
	* Called by the Scheduler when a JobDetail was about to be executed (an associated Trigger has occurred),
	* but a TriggerListener vetoed it's execution.
	*/
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		
	}
	/**
	* (3)
	* 任务执行完成后执行,jobException如果它不为空则说明任务在执行过程中出现了异常
	* Called by the Scheduler after a JobDetail has been executed, and be for the associated Trigger's triggered(xx) method has been called.
	*/
	@Override
	public void jobWasExecuted(final JobExecutionContext context, JobExecutionException jobException) {
		int msgSize = 5000;
		Timestamp end = new Timestamp(System.currentTimeMillis());
		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		if (TaskConstants.ERROR_STATS.equals(jobDataMap.get("taskStatus"))) {
			return;
		}
		String groupName = context.getJobDetail().getKey().getGroup();
		String jobName = context.getJobDetail().getKey().getName();
		if (logger.isInfoEnabled()) {
			logger.info("Listened job : " + groupName + "." + jobName + " executed.");
		}
		// 更新任务执行状态
		final TaskExecLog log = (TaskExecLog) jobDataMap.get(TaskConstants.JOB_LOG);
		System.out.println("日志id："+log.getId());
		log.setEndTime(end);
		if(jobException != null) {
			StringBuffer errorMessage = new StringBuffer();
			InvocationTargetException targetException = (InvocationTargetException) jobException.getCause();
			StackTraceElement[] stackTraceElement = targetException.getTargetException()
					.getStackTrace();
			errorMessage.append(targetException.getTargetException().getMessage() + "\n");
			for (StackTraceElement stackTrace : stackTraceElement) {
				errorMessage.append(stackTrace.toString() + "\n");
			}
			log.setResultCode(TaskConstants.ERROR_STATS);
			log.setResultMsg(StringUtil.toLengthForIntroduce(errorMessage.toString(), msgSize));
		}else {
			if (log.getResultCode().equals(TaskConstants.INIT_STATS)) {
				log.setResultCode(TaskConstants.SUCCESS_STATS);
				log.setResultMsg(TaskConstants.SUCCESS_STATS);
				log.setExexTime(context.getJobRunTime());
			}
		}
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					taskExexLogService.updateDB(log);
				}catch(Exception e) {
					logger.error(
							"Update TaskRunLog cause error. The log object is : "
									+ JSON.toJSONString(log), e);
				}
				// 更新任务执行时间
				TaskScheduler scheduler = taskSchedulerService.queryDBById(jobDataMap.getLong("id"));
				scheduler.setTaskLastExecTime(context.getFireTime());
				scheduler.setTaskLastExecTimes(context.getJobRunTime());
				scheduler.setTaskNextExecTime(context.getNextFireTime());
				taskSchedulerService.updateDB(scheduler);
			}
			
		});
	}

}
