package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.UUID;

public class TPoolSubmitRunnableStr {
	//这种方法 同样类似于传入了公共变量而已....
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor tpe  = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadFactorySelf());
		long start = System.currentTimeMillis();
		StringBuilder res_1 = new StringBuilder();
		StringBuilder res_2 = new StringBuilder();
		Future<StringBuilder> f1 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				res_1.concat("china"); 
				res_1.append("china"); //res_1 = "china" 等于赋值， 而此时默认res_1为 final类型的引用....不可,但是res_1指向的内容是可以改变use concat();
				System.out.println(Thread.currentThread().getName() + "-executing" );
			}
		},res_1);
		
		Future<StringBuilder> f2 = tpe.submit(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				res_2.concat("america"); 
				res_2.append("america"); //res_2 = "america" 等于赋值， 而此时默认res_1为 final类型的引用....不可,但是res_1指向的内容是可以改变,SO,use concat();
				System.out.println(Thread.currentThread().getName() + "-executing" );
				
			}
		},res_2);
		// 内容不能更改， 可见concat()函数 可能返回了一个新的字符串...致使线程执行完res_1 res_2不能改变.
		// 内容可以更改， 可见append()函数 可能StringBuilder的append()函数不能返回新字符串,线程执行完可以满足要求
		System.out.println(f1.get());
		System.out.println(f2.get());
		long end = System.currentTimeMillis();
		System.out.println("Thread complete!" + "--" + (end-start));
		
		String  a = new String();
		
		System.out.println(a.concat("aaa"));
		
		
		
	}

}
