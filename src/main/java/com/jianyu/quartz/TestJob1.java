package com.jianyu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob1 implements BaseJob {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Job 1 is running ... ...");
		
	}
}
