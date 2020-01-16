package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;




public class TPoolSubmitRunnable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor tpe  = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadFactorySelf());
		long start = System.currentTimeMillis();
		Future f1 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-executing" );
			}
		});
		Future f2 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-executing" );
			}
		});
		
		System.out.println(f1.get() == null);
		System.out.println(f2.get() == null);
		long end = System.currentTimeMillis();
		System.out.println("Thread complete!" + "--" + (end-start));
		
	}

}
