package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock2 {
	//多线程修改同一个数据 效率都不行???
	public static int LOOP = 10000 * 10000;
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor  tpe  = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));
		
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
		Future f_sync_1 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					h.subsync(1);
				}
			}
		});
		
		Future f_sync_2 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					h.addsync(1);
				}
			}
		});
		f_sync_1.get();
		f_sync_2.get();
		long end_sync = System.currentTimeMillis();
		System.out.println("[sync]-final result="  + h.get() + "--start="+start_sync+ "--cost=" +(end_sync-start_sync) ); 
		
		//-------------------------------------------
		//------------------Synchronized 类外锁---------------------------
		long start_out_sync = System.currentTimeMillis();
		Future f_sync_out_1 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					synchronized (h) {
						h.sub(1);
					}
				}
			}
		});
		
		Future f_sync_out_2 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					synchronized (h) {
						h.add(1);
					}
				}
			}
		});
		f_sync_out_1.get();
		f_sync_out_2.get();
		long end_out_sync = System.currentTimeMillis();
		System.out.println("[osyn]-final result="  + h.get() + "--start="+start_out_sync+ "--cost=" +(end_out_sync-start_out_sync) ); 
		
		//-------------------------------------------
		//-----------Lock 类内锁--------------------------------
		long start_lock = System.currentTimeMillis();
		Future f_lock_1 = tpe.submit(new Runnable() {
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
		
		Future f_lock_2 = tpe.submit(new Runnable() {
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
		f_lock_1.get();
		f_lock_2.get();
		long end_lock = System.currentTimeMillis();
		System.out.println("[lock]-final result="  + h.get() +  "--start="+start_lock+ "--cost=" +(end_lock-start_lock) ); 
		
		
		//-------------------------------------------
		//--------------Lock 类外锁-----------------------------
		Lock rawLock = new ReentrantLock();
		long start_outLock = System.currentTimeMillis();
		Future f_lock_out_1 = tpe.submit(new Runnable() {
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
		
		Future f_lock_out_2 = tpe.submit(new Runnable() {
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
		f_lock_out_1.get();
		f_lock_out_2.get();
		long end_outLock = System.currentTimeMillis();
		System.out.println("[olck]-final result="  + h.get() + "--start="+start_outLock+ "--cost=" +(end_outLock-start_outLock) ); 
		
		//-------------------------------------------
		//-------------Atomic 原子整数------------------------------
		long start_yz = System.currentTimeMillis();
		Future f_atomic_1 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					h.subyz(1);
				}
			}
		});
		
		Future f_atomic_2 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < LOOP; i++) {
					h.addyz(1);
				}
			}
		});
		f_atomic_1.get();
		f_atomic_2.get();
		long end_yz = System.currentTimeMillis();
		System.out.println("[yztt]-final result="  + h.get() +  "--start="+start_yz+ "--cost=" +(end_yz-start_yz) ); 
		
		tpe.shutdown();
	}

}
