package ProducerConsumer;

import mystack.MyStack2;

public class ConsumerThread2  extends Thread{

	private MyStack2<Character> stack ; 
	
	public ConsumerThread2(MyStack2<Character> stack, String name) {
		super(name);
		this.stack = stack;
	}
	
	public void run() {
		while(true) {
			char c = stack.pull();
			System.out.println(this.getName() + "弹出" + c);
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
