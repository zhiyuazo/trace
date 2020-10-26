package com.zzy.trace.mystack;

import java.util.LinkedList;

public class MyStack<T> {
	
	LinkedList<T> datas = new LinkedList<T>();
	
	public synchronized void push(T t) {
		while(datas.size() >= 200) {
			try {
				this.wait();
			} catch ( InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
		datas.addLast(t);
	}
	
	public synchronized T pull() {
		while(datas.isEmpty()) {
			try {
				this.wait();
			} catch ( InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
		return datas.removeLast();
	}
	
	public T peek() {
		return datas.getLast();
	}
}
