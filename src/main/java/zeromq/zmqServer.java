package zeromq;

import org.zeromq.ZMQ;

public class zmqServer {
	
	public static void main (String[] args) {
		ZMQ.Context context = ZMQ.context(1);  
		
		ZMQ.Socket socket = context.socket(ZMQ.REP);  
		socket.bind ("tcp://*:5555");    
		int i = 0;
		int number = 0;
		while (!Thread.currentThread().isInterrupted()) {
			i++;
			if (i == 10000) {
				i = 0;
				System.out.println(++number);
			}
			byte[] request = socket.recv();  
			System.out.println("receive : " + new String(request));
			String response = "world";
			socket.send(response.getBytes());  
		}
		socket.close();
		context.term();
    }
}
