package testHub;

import com.zzy.trace.blockQueue.PutThread;
import com.zzy.trace.blockQueue.TakeThread;

public class testHub {
	
	public static void main(String[] args) throws InterruptedException {
			
		PutThread.getInstance().start();
		TakeThread.getInstance().start();
		
		Thread.sleep(5000);
		
		for (short i = 'A'; i < 'Z'; i++) {
			char tmp = (char)i;
			PutThread.getInstance().put(String.valueOf(tmp));
			Thread.sleep(1000);
		}
	}
}
