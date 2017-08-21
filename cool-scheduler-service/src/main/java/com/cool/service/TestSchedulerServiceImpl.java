package com.cool.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.cool.api.scheduler.TestSchedulerService;

@Service
public class TestSchedulerServiceImpl implements TestSchedulerService{

	@Override
	public void testShceduler() {
		System.out.println("执行定时任务:"+new Date());	
	}

}
