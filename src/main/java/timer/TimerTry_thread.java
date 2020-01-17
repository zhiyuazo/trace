package timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerTry_thread {
	private static Logger logger = LoggerFactory.getLogger(TimerTry_thread.class);
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					
					System.out.println("Thread + Runnable to achieve Timer Function...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t1.start();
		t1.join();
	}

}
