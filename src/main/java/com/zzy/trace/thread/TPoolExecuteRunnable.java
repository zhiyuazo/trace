package com.zzy.trace.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;




public class TPoolExecuteRunnable {
	public static   int a = 1;
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor tpe  = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadFactorySelf());
		long start = System.currentTimeMillis();
		tpe.execute(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-executing" );
				a = a+1;
			}
		});
		tpe.execute(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-executing" );
				a = a+1;
			}
		});
		
		while(a != 3) {
			System.out.println(tpe.getActiveCount() + ":" + tpe.getQueue().size());
			Thread.currentThread().sleep(1000);
		}
		
		tpe.shutdownNow();
		long end = System.currentTimeMillis();
		System.out.println("Thread complete!" + "--" + (end-start));
		
	}

}
