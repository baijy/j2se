package com.jianyu.quartz;

import java.util.concurrent.TimeUnit;

public class SchedulerTest {
	static String jobClassName = "com.jianyu.quartz.TestJob1";
	static String jobName = "job1";
	static String jobGroupName = "defaultGroup";
	static String cronExpression = "*/2 * * * * ?";// 2秒执行一次
	static String jobDataMap = "";

	public static void main(String[] args) throws Exception {
		SchedulerUtil.addJob(jobClassName, jobName, jobGroupName, cronExpression, jobDataMap);
		TimeUnit.SECONDS.sleep(6);
		
		SchedulerUtil.jobPause(jobName, jobGroupName);
		TimeUnit.SECONDS.sleep(6);
		
		SchedulerUtil.jobReschedule(jobName, jobGroupName, cronExpression);
		TimeUnit.SECONDS.sleep(6);
		
		SchedulerUtil.pauseAlljob();
	}
}
