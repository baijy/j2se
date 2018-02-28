package com.jianyu.quartz;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class SchedulerUtil {
	private static Logger logger = LoggerFactory.getLogger(SchedulerUtil.class);
	
	/**
	 * 新增定时任务
	 * @param jobClassName 类路径
	 * @param jobName 任务名称
	 * @param jobGroupName 组别
	 * @param cronExpression
	 * @throws Exception
	 */
	public static void addJob(String jobClassName,String jobName, String jobGroupName, String cronExpression,String jobDataMap) throws Exception {
		// 通过SchedulerFactory获取一个调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		// 启动调度器
		sched.start();
		// 构建job信息
		JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
				.withIdentity(jobName, jobGroupName).build();
		if (StringUtils.isNotEmpty(jobDataMap)) {
			JSONObject jb = JSONObject.parseObject(jobDataMap);
			Map<String, Object> dataMap =(Map<String, Object>) jb.get("data");
			for (Map.Entry<String, Object> m:dataMap.entrySet()) {
				jobDetail.getJobDataMap().put(m.getKey(),m.getValue());
			}
		}
		// 表达式调度构建器(即任务执行的时间)
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
				.withSchedule(scheduleBuilder).startNow().build();
		try {
			sched.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			logger.info("创建定时任务失败" + e);
			throw new Exception("创建定时任务失败");
		}
	}
	
	/**
	 * 停用一个定时任务
	 * @param jobName 任务名称
	 * @param jobGroupName 组别
	 * @throws Exception
	 */
	public static void jobPause(String jobName, String jobGroupName) throws Exception {
		// 通过SchedulerFactory获取一个调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		logger.info("\n停用任务[ "+jobName+" ]开始... ...");
		sched.pauseJob(JobKey.jobKey(jobName, jobGroupName));
	}
	
	/**
	 * 启用一个定时任务
	 * @param jobName 任务名称
	 * @param jobGroupName 组别
	 * @throws Exception
	 */
	public static void jobresume(String jobName, String jobGroupName) throws Exception {
		// 通过SchedulerFactory获取一个调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		logger.info("\n启用任务[ "+jobName+" ]开始... ...");
		sched.resumeJob(JobKey.jobKey(jobName, jobGroupName));
	}
	
	/**
	 * 删除一个定时任务
	 * @param jobName 任务名称
	 * @param jobGroupName 组别
	 * @throws Exception
	 */
	public static void jobdelete(String jobName, String jobGroupName) throws Exception {
		// 通过SchedulerFactory获取一个调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		logger.info("\n删除定时任务[ "+ jobName +" ]开始");
		sched.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
		sched.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
		sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
	}
	
	/**
	 * 更新定时任务表达式
	 * @param jobName 任务名称
	 * @param jobGroupName 组别
	 * @param cronExpression Cron表达式
	 * @throws Exception
	 */
	public static void jobReschedule(String jobName, String jobGroupName, String cronExpression) throws Exception {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).startNow().build();
			// 按新的trigger重新设置job执行
			logger.info("\n变更定时任务[ "+ jobName +" ]表达式开始");
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			System.out.println("更新定时任务失败" + e);
			throw new Exception("更新定时任务失败");
		}
	}
	
	/**
	 * 检查Job是否存在
	 * @throws Exception
	 */
	public static Boolean isResume(String jobName, String jobGroupName) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
		Boolean state = sched.checkExists(triggerKey);
	     
		return state;
	}

	
	/**
	 * 暂停所有任务
	 * @throws Exception
	 */
	public static void pauseAlljob() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.pauseAll();
	}

	/**
	 * 唤醒所有任务
	 * @throws Exception
	 */
	public static void resumeAlljob() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.resumeAll();

	}
	
	public static BaseJob getClass(String classname) throws Exception {
		try {
			Class<?> c = Class.forName(classname);
			return (BaseJob) c.newInstance();
		} catch (Exception e) {
			//throw new BizException("类["+classname+"]不存在！");
		}
		return null;
		
	}

}

