package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

public class HeroLock {
	String name ; 
	int age =  0;
	AtomicInteger age_yz = new AtomicInteger(0);
	int sleep = 2;
	public  Lock lock = new ReentrantLock();
	
	public HeroLock(String s , int a) {
		this.name =s ;
		this.age = a ;
	}
	
	public  void add(int up) {
		this.age += up ;
	}
	
	public  void sub(int down) {
		this.age -= down ;
	}
	
	public synchronized void addsync(int up) {
		this.age += up ;
	}
	
	public synchronized void subsync(int down) {
		this.age -= down ;
	}
	
	public  void addlock(int up) throws InterruptedException {
		lock.lock();
		try {
			this.age += up ;
		} finally {
			lock.unlock();
		}
	}
	public  void sublock(int down) throws InterruptedException {
		lock.lock();
		try {
			this.age -=down ;
		} finally {
			lock.unlock();
		}
	}
	
	public  void subyz(int down) {
		this.age_yz.getAndAdd(-down);
	}
	public  void addyz(int up) {
		this.age_yz.getAndAdd(up);
	}
	
	public int get() {
		return this.age;
	}
	
	public AtomicInteger getyz() {
		return this.age_yz;
	}

}
