package com.zzy.trace.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
	//多线程修改同一个数据 效率都不行???
	public static int LOOP = 10000 * 10000;
	public static void main(String[] args) throws InterruptedException {
		
		HeroLock h = new HeroLock("zzy",30);
		
		//-------------------------------------------
		//-----------------单线程 顺序增减..--------------------------
		long start_sequ = System.currentTimeMillis();
		for (int i = 0; i < LOOP; i++) {
			h.add(1);
		}
		for (int i = 0; i < LOOP; i++) {
			h.sub(1);
		}
		long end_sequ = System.currentTimeMillis();
		System.out.println("[sequ]-final result="  + h.get() + "--start="+start_sequ+ "--cost=" +(end_sequ-start_sequ) ); 
		
		
		//-------------------------------------------
		//------------------Synchronized 类内修饰-------------------------
		long start_sync = System.currentTimeMillis();
		Thread t_sync_sub = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					h.subsync(1);
				}
			}
		});
		
		Thread t_sync_add = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					h.addsync(1);
				}
			}
		});
		
		t_sync_sub.start();
		t_sync_add.start();
		t_sync_sub.join();
		t_sync_add.join();
		
		long end_sync = System.currentTimeMillis();
		System.out.println("[sync]-final result="  + h.get() + "--start="+start_sync+ "--cost=" +(end_sync-start_sync) ); 
		
		//-------------------------------------------
		//------------------Synchronized 类外锁---------------------------
		long start_out_sync = System.currentTimeMillis();
		Thread t_sync_out_sub = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					synchronized (h) {
						h.sub(1);
					}
				}
			}
		});
		
		Thread t_sync_out_add = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					synchronized (h) {
						h.add(1);
					}
				}
			}
		});
		
		t_sync_out_sub.start();
		t_sync_out_add.start();
		t_sync_out_sub.join();
		t_sync_out_add.join();
		
		long end_out_sync = System.currentTimeMillis();
		System.out.println("[osyn]-final result="  + h.get() + "--start="+start_out_sync+ "--cost=" +(end_out_sync-start_out_sync) ); 
		
		//-------------------------------------------
		//-----------Lock 类内锁--------------------------------
		long start_lock = System.currentTimeMillis();
		Thread t_lock_sub = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					try {
						h.sublock(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t_lock_add = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					try {
						h.addlock(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t_lock_sub.start();
		t_lock_add.start();
		t_lock_sub.join();
		t_lock_add.join();
		
		long end_lock = System.currentTimeMillis();
		System.out.println("[lock]-final result="  + h.get() +  "--start="+start_lock+ "--cost=" +(end_lock-start_lock) ); 
		
		
		//-------------------------------------------
		//--------------Lock 类外锁-----------------------------
		Lock rawLock = new ReentrantLock();
		long start_outLock = System.currentTimeMillis();
		Thread t_lock_out_sub = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					rawLock.lock();
					try {
						h.sub(1);
					} finally {
						rawLock.unlock();
					}
				}
			}
		});
		
		Thread t_lock_out_add = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					rawLock.lock();
					try {
						h.add(1);
					} finally {
						rawLock.unlock();
					}
				}
			}
		});
		t_lock_out_sub.start();
		t_lock_out_add.start();
		t_lock_out_sub.join();
		t_lock_out_add.join();
		
		long end_outLock = System.currentTimeMillis();
		System.out.println("[olck]-final result="  + h.get() + "--start="+start_outLock+ "--cost=" +(end_outLock-start_outLock) ); 
		
		//-------------------------------------------
		//-------------Atomic 原子整数------------------------------
		long start_yz = System.currentTimeMillis();
		Thread t_yz_sub = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					h.subyz(1);
				}
			}
		});
		
		Thread t_yz_add = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					h.addyz(1);
				}
			}
		});
		
		t_yz_sub.start();
		t_yz_add.start();
		t_yz_sub.join();
		t_yz_add.join();
		
		long end_yz = System.currentTimeMillis();
		System.out.println("[yztt]-final result="  + h.get() +  "--start="+start_yz+ "--cost=" +(end_yz-start_yz) ); 
		
	}

}
