package com.zzy.trace.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TPoolFutureTask {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 60, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(10));
		
		long start = System.currentTimeMillis();
		FutureTask<String>  ft1 = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "china";
			}
		});
		
		tpe.execute(ft1);
		
		FutureTask<String>  ft2 = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "english";
			}
		});
		tpe.execute(ft2);
		
		
		while (!ft1.isDone() && !ft2.isDone()) {
			Thread.sleep(1);
		}
		System.out.println(ft1.get());
		System.out.println(ft2.get());
		long end = System.currentTimeMillis();
		System.out.println("cost--" + (end-start));
		tpe.shutdown();
	}
	
	

}
