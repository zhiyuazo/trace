package timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerTry_scheduleThread {
	private static Logger logger = LoggerFactory.getLogger(TimerTry_scheduleThread.class);
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ScheduledThreadPoolExecutor stpe =  new ScheduledThreadPoolExecutor(1);
		Runnable  runnable = new Runnable() {
			@Override
			public void run() {
				long now  =  System.currentTimeMillis();
				logger.info("JAVA-Timer-Way-{},Away from start {}" , "zhiyuan" ,(now-start)/1000);
			}
		};
		
		long start =  System.currentTimeMillis();
		Future f = stpe.scheduleAtFixedRate(runnable,2000,1000, TimeUnit.SECONDS);
		System.out.println("??");
		System.out.println(f.get());
	}

}
