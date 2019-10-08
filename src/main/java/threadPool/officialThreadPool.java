package threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class officialThreadPool {
	public static void main(String[] args) {
		ThreadPoolExecutor tp = new ThreadPoolExecutor(10, 
														15, 
														60, 
														TimeUnit.SECONDS, 
														new LinkedBlockingQueue<Runnable>());
		tp.execute(new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName() + " run task-1");
			}
		});
	}

}
