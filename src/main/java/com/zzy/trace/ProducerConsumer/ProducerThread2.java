package com.zzy.trace.ProducerConsumer;

import com.zzy.trace.mystack.MyStack2;

public class ProducerThread2  extends Thread{

	private MyStack2<Character> stack ; 
	
	public ProducerThread2(MyStack2<Character> stack, String name) {
		super(name);
		this.stack = stack;
	}
	
	public void run() {
		while(true) {
			char c = randomChar();
			System.out.println(this.getName() + "压入" + c);
			stack.push(c);
			try {
				Thread.sleep(100);
				
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public char randomChar() {
		return (char) (Math.random()*('Z'+1-'A') +'A');
	}
	
	
	
}
