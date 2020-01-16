package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.UUID;



class RES {
	String name ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

class TASK  implements Runnable{
	RES res_in ;
	public TASK(RES res ) {
		this.res_in = res; 
	}
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		res_in.setName("CHINA:-" + UUID.randomUUID());
		System.out.println(Thread.currentThread().getName() + "-executing" );
		
	}
	
}


public class TPoolSubmitRunnableObj {
	//这种方法 同样类似于传入了公共变量而已....
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor tpe  = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadFactorySelf());
		long start = System.currentTimeMillis();
		RES res_1 = new RES();
		RES res_2 = new RES();
		Future<RES> f1 = tpe.submit(new TASK(res_1),res_1);
		Future<RES> f2 = tpe.submit(new TASK(res_2),res_2);
		
		System.out.println(f1.get().getName());
		System.out.println(f2.get().getName());
		
		System.out.println(res_1.getName());
		System.out.println(res_2.getName());
		long end = System.currentTimeMillis();
		System.out.println("Thread complete!" + "--" + (end-start));
		
	}

}
