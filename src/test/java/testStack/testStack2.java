package testStack;

import ProducerConsumer.ConsumerThread2;
import ProducerConsumer.ProducerThread2;
import mystack.MyStack2;

public class testStack2 {
	public static void main(String[] args) {
		
		MyStack2<Character> stack = new MyStack2<>();
		new ProducerThread2(stack,"P1").start();
		new ProducerThread2(stack,"P2").start();
		new ConsumerThread2(stack,"C1").start();
		new ConsumerThread2(stack,"C2").start();
		new ConsumerThread2(stack,"C3").start();
	}
}
