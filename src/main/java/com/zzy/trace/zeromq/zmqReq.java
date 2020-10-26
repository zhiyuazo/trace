package com.zzy.trace.zeromq;

import org.zeromq.ZMQ;

public class zmqReq {
	public static void main(String args[]) {
		for (int j = 0;  j < 5; j++) {
			new Thread(new Runnable(){
				public void run() {
					ZMQ.Context context = ZMQ.context(1);  
					ZMQ.Socket socket = context.socket(ZMQ.REQ);  
					
					socket.connect("tcp://127.0.0.1:5555"); 
					long now = System.currentTimeMillis();
					for (int i = 0; i < 100000; i++) {
						String request = "hello";
						socket.send(request.getBytes()); 
						byte[] response = socket.recv();
						System.out.println(Thread.currentThread().getName() + "-receive : " + new String(response));
					}
					long after = System.currentTimeMillis();
					System.out.println((after - now) / 1000);
				}
			},"zmq-client-"+j).start();;
		}
		
	}
}
