package com.zzy.trace.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerTry_scheduleThread {
	private static Logger logger = LoggerFactory.getLogger(TimerTry_scheduleThread.class);
	//多线程,
	//可调度多个任务，
	//线程安全
	//设置delay时间
	//取消任务方便
	//误差可以容忍忽略不计 
	//可以创建多个线程。解决任务执行的时间>设置的延时时间的问题。
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ScheduledThreadPoolExecutor stpe =  new ScheduledThreadPoolExecutor(2);
		
		long start =  System.currentTimeMillis();
		stpe.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				long now  =  System.currentTimeMillis();
				logger.info("JAVA-ScheduleThread-Way-{},Away from start {}, type=FixedRate" , Thread.currentThread().getName() ,(now-start)/1000);
			}
		},2000,1000, TimeUnit.MILLISECONDS);
		
		stpe.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				long now  =  System.currentTimeMillis();
				logger.info("JAVA-ScheduleThread-Way-{},Away from start {}, type=FixedDelay" , Thread.currentThread().getName() ,(now-start)/1000);
			}
		},2000,1000, TimeUnit.MILLISECONDS);
		
		
		stpe.schedule(new Runnable() {
			@Override
			public void run() {
				long now  =  System.currentTimeMillis();
				logger.info("JAVA-ScheduleThread-Way-{},Away from start {}, type=Once" , Thread.currentThread().getName() ,(now-start)/1000);
			}
		}, 2000, TimeUnit.MILLISECONDS);
		System.out.println("??");
//		System.out.println(f.get());
	}

}
