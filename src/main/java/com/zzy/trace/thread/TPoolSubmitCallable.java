package com.zzy.trace.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;




public class TPoolSubmitCallable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor tpe  = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadFactorySelf());
		long start = System.currentTimeMillis();
		Future f1 = tpe.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-executing" );
				return "zhiyuan";
			}
		});
		Future f2=  tpe.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-executing" );
				return "lqg";
			}
		});
		
		System.out.println(f1.get());
		System.out.println(f2.get());
		
		long end = System.currentTimeMillis();
		System.out.println("Thread complete!" + "--" + (end-start));
		
	}

}
