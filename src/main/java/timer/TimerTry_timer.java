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
	//误差 可能60ms / 1 min
	//Timer只创建了一个线程。当你的任务执行的时间超过设置的延时时间将会产生一些问题。
	//Timer创建的线程没有处理异常，因此一旦抛出非受检异常，该线程会立即终止
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
