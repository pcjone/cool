package com.cool.service.manager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.cool.api.manager.SchedulerManager;
import com.cool.model.expand.TaskSchedulerModel;
import com.cool.util.InstanceUtil;
public class DefaultSchedulerManager implements SchedulerManager, InitializingBean{
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private Scheduler scheduler;
	
	private List<TriggerLoader> triggerLoaders;

	private List<JobListener> jobListeners;

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void setTriggerLoaders(List<TriggerLoader> triggerLoaders) {
		this.triggerLoaders = triggerLoaders;
	}

	public void setJobListeners(List<JobListener> jobListeners) {
		this.jobListeners = jobListeners;
	}

	/**
	 * 调度初始化
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.jobListeners != null && this.jobListeners.size() > 0) {
			if (logger.isInfoEnabled()) {
				logger.info("Initing task scheduler[" + this.scheduler.getSchedulerName()
						+ "] , add listener size ：" + this.jobListeners.size());
			}
			for (JobListener listener : this.jobListeners) {
				if (logger.isInfoEnabled()) {
					logger.info("Add JobListener : " + listener.getName());
				}
				this.scheduler.getListenerManager().addJobListener(listener);
			}
		}
		// 根据配置的初始化装载
				if (this.triggerLoaders != null && this.triggerLoaders.size() > 0) {
					if (logger.isInfoEnabled()) {
						logger.info("Initing task scheduler[" + this.scheduler.getSchedulerName()
								+ "] , trigger loaders size ：" + this.triggerLoaders.size());
					}
					for (TriggerLoader loader : this.triggerLoaders) {
						if (logger.isInfoEnabled()) {
							logger.info("Initing triggerLoader[" + loader.getClass().getName() + "].");
						}
						Map<Trigger, JobDetail> loadResultMap = loader.loadTriggers();
						if (loadResultMap != null) {
							for (Entry<Trigger, JobDetail> entry : loadResultMap.entrySet()) {
								this.addJobDetail(entry.getValue());
								this.addTrigger(entry.getKey());
							}
							if (logger.isInfoEnabled()) {
								logger.info("Initing triggerLoader[" + loader.getClass().getName()
										+ "] end .");
							}
						} else {
							logger.warn("No triggers loaded by triggerLoader["
									+ loader.getClass().getName() + "].");
						}
					}
				} else {
					logger.warn("No TriggerLoader for initing.");
				}
	}
	
	private void addJobDetail(JobDetail jobDetail) {
		JobDetail oldJobDetail = null;
		try {
			oldJobDetail = this.scheduler.getJobDetail(jobDetail.getKey());
		} catch (Exception e) {
		}
		try {
			if (oldJobDetail == null) {
				if (logger.isInfoEnabled()) {
					logger.info("Try to add jobDetail : " + jobDetail);
				}
				this.scheduler.addJob(jobDetail, true);
			} else {
				updateJobDetail(jobDetail);
			}
		} catch (Exception e) {
			logger.error(
					"Try to add jobDetail : " + jobDetail + ", the old jobDetail is : "
							+ (oldJobDetail == null ? "null" : oldJobDetail.toString())
							+ " cause error : ", e);
		}
	}
	
	private void updateJobDetail(JobDetail jobDetail) {
		JobDetail oldJobDetail = null;
		try {
			oldJobDetail = this.scheduler.getJobDetail(jobDetail.getKey());
		} catch (Exception e) {
		}
		try {
			if (oldJobDetail != null) {
				if (logger.isInfoEnabled()) {
					logger.info("Try to update oldJobDetail : " + oldJobDetail);
				}
				this.scheduler.addJob(jobDetail, true);
			} else {
				logger.warn("Can't update JobDetail : " + jobDetail);
			}
		} catch (SchedulerException e) {
			logger.error(
					"Try to update JobDetail : " + jobDetail + ", the old JobDetail is : "
							+ (oldJobDetail == null ? "null" : oldJobDetail.toString())
							+ " cause error : ", e);
		}
	}
	
	private void addTrigger(Trigger trigger) {
		Trigger oldTrigger = null;
		try {
			oldTrigger = scheduler.getTrigger(trigger.getKey());
		} catch (Exception e) {
		}
		try {
			if (oldTrigger == null) {
				if (logger.isInfoEnabled()) {
					logger.info("Try to add trigger : " + trigger);
				}
				scheduler.scheduleJob(trigger);
				if (!trigger.getJobDataMap().getBoolean("enable")) {
					scheduler.pauseTrigger(trigger.getKey());
				}
			} else {
				updateTrigger(trigger);
			}
		} catch (SchedulerException e) {
			logger.error("Try to add trigger : " + trigger + "  cause error : ", e);
		}
	}
	
	private void updateTrigger(Trigger trigger) {
		Trigger oldTrigger = null;
		try {
			oldTrigger = scheduler.getTrigger(trigger.getKey());
		} catch (Exception e) {
		}
		try {
			if (oldTrigger != null) {
				if (logger.isInfoEnabled()) {
					logger.info("Try to update trigger : " + trigger);
				}
				scheduler.rescheduleJob(trigger.getKey(), trigger);
				if (!trigger.getJobDataMap().getBoolean("enable")) {
					scheduler.pauseTrigger(trigger.getKey());
				}
			} else {
				logger.warn("Can't update trigger : " + trigger);
			}
		} catch (SchedulerException e) {
			logger.error("Try to update trigger : " + trigger + ", the old trigger is : "
					+ (oldTrigger == null ? "null" : oldTrigger.toString()) + " cause error : ", e);
		}
	}

	@Override
	public List<TaskSchedulerModel> getAllJobDetail() {
		List<TaskSchedulerModel> result = new LinkedList<TaskSchedulerModel>();
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupContains("");
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					TaskSchedulerModel job = new TaskSchedulerModel();
					job.setTaskName(jobKey.getName());
					job.setGroupName(jobKey.getGroup());
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					job.setResultCode(triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						job.setTimeConfig(cronExpression);
					}
					job.setTaskLastExecTime(trigger.getPreviousFireTime());
					job.setTaskNextExecTime(trigger.getNextFireTime());
					job.setTaskDesc(trigger.getJobDataMap().getString("desc"));
					result.add(job);
				}
			}
			for (int i = 1; i < result.size(); i++) {
				if (result.get(i).getTaskName().equals(result.get(i - 1).getTaskName())
						&& result.get(i).getTaskLastExecTime() != null) {
					result.get(i - 1).setTaskLastExecTime(result.get(i).getTaskLastExecTime());
					result.remove(i);
					i--;
				}
			}
		}catch(Exception e) {
			logger.error("Try to load All JobDetail cause error : ", e);
		}
		return result;
	}

	@Override
	public JobDetail getJobDetailByTriggerName(Trigger trigger) {
		try {
			return this.scheduler.getJobDetail(trigger.getJobKey());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<TaskSchedulerModel> getRunningJobDetail() {
		List<TaskSchedulerModel> jobList = null;
		try {
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			jobList = new ArrayList<TaskSchedulerModel>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				TaskSchedulerModel job = new TaskSchedulerModel();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				job.setTaskName(jobKey.getName());
				job.setGroupName(jobKey.getGroup());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setResultCode(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setTimeConfig(cronExpression);
				}
				job.setTaskLastExecTime(trigger.getPreviousFireTime());
				job.setTaskNextExecTime(trigger.getNextFireTime());
				job.setTaskDesc(trigger.getJobDataMap().getString("desc"));
				jobList.add(job);
			}
		}catch (Exception e) {
			logger.error("Try to load running JobDetail cause error : ", e);
		}
		return null;
	}

	@Override
	public boolean stopJob(TaskSchedulerModel scheduleJob) {
		try {
			JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getGroupName());
			scheduler.pauseJob(jobKey);
			return true;
		} catch (Exception e) {
			logger.error("Try to stop Job cause error : ", e);
		}
		return false;
	}

	@Override
	public boolean resumeJob(TaskSchedulerModel scheduleJob) {
		try {
			JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getGroupName());
			scheduler.resumeJob(jobKey);
			return true;
		} catch (Exception e) {
			logger.error("Try to resume Job cause error : ", e);
		}
		return false;
	}

	@Override
	public boolean runJob(TaskSchedulerModel scheduleJob) {
		try {
			JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getGroupName());
			System.out.println(scheduleJob.getTaskName());
			scheduler.getContext().put(
					scheduleJob.getTaskName() + ":" + scheduleJob.getGroupName(), "1");
			scheduler.triggerJob(jobKey);
			return true;
		} catch (Exception e) {
			logger.error("Try to resume Job cause error : ", e);
		}
		return false;
	}

	@Override
	public boolean refreshScheduler() {
		try {
			// 根据配置的初始化装载
			if (this.triggerLoaders != null && this.triggerLoaders.size() > 0) {
				if (logger.isInfoEnabled()) {
					logger.info("Initing task scheduler[" + this.scheduler.getSchedulerName()
							+ "] , trigger loaders size ：" + this.triggerLoaders.size());
				}
				// 获取原始调度状态
				List<TaskSchedulerModel> scheduleJobs = getAllJobDetail();
				Map<String, String> stateMap = InstanceUtil.newHashMap();
				for (TaskSchedulerModel scheduleJob : scheduleJobs) {
					stateMap.put(scheduleJob.getGroupName() + "." + scheduleJob.getTaskName(),
							scheduleJob.getResultCode());
				}
				// 清空调度
				scheduler.clear();
				// 加载调度
				for (TriggerLoader loader : this.triggerLoaders) {
					if (logger.isInfoEnabled()) {
						logger.info("Initing triggerLoader[" + loader.getClass().getName() + "].");
					}
					Map<Trigger, JobDetail> loadResultMap = loader.loadTriggers();
					if (loadResultMap != null) {
						for (Entry<Trigger, JobDetail> entry : loadResultMap.entrySet()) {
							this.addJobDetail(entry.getValue());
							this.addTrigger(entry.getKey());
							JobKey jobKey = entry.getValue().getKey();
							String key = jobKey.getGroup() + "." + jobKey.getName();
							// 新增任务或原来为暂停状态
							if ("PAUSED".equals(stateMap.get(key)) || !stateMap.containsKey(key)) {
								scheduler.pauseJob(jobKey);
							}
						}
						if (logger.isInfoEnabled()) {
							logger.info("Initing triggerLoader[" + loader.getClass().getName()
									+ "] end .");
						}
					} else {
						logger.warn("No triggers loaded by triggerLoader["
								+ loader.getClass().getName() + "].");
					}
				}
			} else {
				logger.warn("No TriggerLoader for initing.");
			}
			return true;
		} catch (Exception e) {
			logger.error("Try to refresh scheduler cause error : ", e);
		}
		return false;
	}

	@Override
	public void reload() throws Exception {
		refreshScheduler();
	}

	
}
