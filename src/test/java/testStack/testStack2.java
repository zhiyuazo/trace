package testStack;

import com.zzy.trace.ProducerConsumer.ConsumerThread2;
import com.zzy.trace.ProducerConsumer.ProducerThread2;
import com.zzy.trace.mystack.MyStack2;

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
