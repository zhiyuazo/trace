package testStack;

import ProducerConsumer.ConsumerThread;
import ProducerConsumer.ProducerThread;
import mystack.MyStack;

public class testStack {
	public static void main(String[] args) {
		
		MyStack<Character> stack = new MyStack<>();
		new ProducerThread(stack,"P1").start();
		new ProducerThread(stack,"P2").start();
		new ConsumerThread(stack,"C1").start();
		new ConsumerThread(stack,"C2").start();
		new ConsumerThread(stack,"C3").start();
	}
}
