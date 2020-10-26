package com.zzy.trace.thread;

import java.util.LinkedList;

public class ThreadPoolSelf {
	
	int poolsize ;
	
	LinkedList<Runnable> tasks  = new LinkedList<Runnable>();

	public  ThreadPoolSelf() {
		poolsize = 10;
		synchronized (tasks) {
			for (int i = 0; i < poolsize; i++) {
				new TaskConsumeThread("test线程-"+i).start();
			}
		}
	}
	
	public void add(Runnable r) {
		synchronized (tasks) {
			tasks.add(r);
			tasks.notifyAll();
		}
	}
	
	class TaskConsumeThread extends Thread{
		
		public TaskConsumeThread(String n) {
			super(n);
		}
		Runnable task;
		
		public void run() {
			System.out.println("启动线程 " + this.getName() );
			while(true) {
				synchronized (tasks) {
					while(tasks.isEmpty()) {
						try {
							tasks.wait();
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
					task = tasks.removeLast();
					tasks.notifyAll();
				}
				System.out.println(this.getName() + "获取到任务，开始执行。。。");
				task.run();
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadPoolSelf pool  = new ThreadPoolSelf();
		int sleep = 1000;
		while(true) {
			pool.add(new Runnable() {
				public void run() {
					System.out.println("abcdefg");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			try {
				Thread.sleep(sleep);
				sleep = sleep > 100 ? sleep-100:sleep;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
