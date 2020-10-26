package com.zzy.trace.blockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PutThread extends Thread{ 
	private  BlockingQueue<String> pointQueue =  new LinkedBlockingQueue<String>();
	
	private PutThread() {
		
	}
	
	private  static PutThread instance = new PutThread();
	
	public static PutThread getInstance() {
		return instance;
	}
	
	public void put(String t) {
		try {
			System.out.println("inject: " + t);
			pointQueue.put(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("put Thread start!...");
		while(true) {
			try {
				String t = pointQueue.take();
				DataHub.getInstance().put(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


