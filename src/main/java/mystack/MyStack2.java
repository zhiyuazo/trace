package mystack;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyStack2<T> {
	
	LinkedList<T> datas = new LinkedList<T>();
	
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public  void push(T t) {
		try {
			lock.lock();
			while(datas.size() >= 200) {
				try {
					condition.await();
				} catch ( InterruptedException e) {
					e.printStackTrace();
				}
			}
			condition.signalAll();
			datas.addLast(t);
		} finally {
			lock.unlock();
		}
	}
	
	public  T pull() {
		T t = null ;
		try {
			lock.lock();
			while(datas.isEmpty()) {
				try {
					condition.await();;
				} catch ( InterruptedException e) {
					e.printStackTrace();
				}
			}
			condition.signalAll();
			t= datas.removeLast();
			
		} finally {
			lock.unlock();
		}
		return t;
	}
	
	public T peek() {
		return datas.getLast();
	}
}
