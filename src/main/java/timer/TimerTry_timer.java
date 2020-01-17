package timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerTry_timer {
	private static Logger logger = LoggerFactory.getLogger(TimerTry_timer.class);
	
	//单线程,
	//可调度多个任务，
	//线程安全
	//设置delay时间
	//取消任务方便
	public static void main(String[] args) {
		
		long start =  System.currentTimeMillis();
		Timer timer = new Timer("Try-Timer");
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				long now  =  System.currentTimeMillis();
				logger.info("JAVA-Timer-Way-{},Away from start {}" , "zhiyuan" ,(now-start)/1000);
			}
		}, new Date(new Date().getTime()+5000),1000);
		
		System.out.println("??");
		
		
	}

}
