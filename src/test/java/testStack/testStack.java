package testStack;

import com.zzy.trace.ProducerConsumer.ConsumerThread;
import com.zzy.trace.ProducerConsumer.ProducerThread;
import com.zzy.trace.mystack.MyStack;

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
