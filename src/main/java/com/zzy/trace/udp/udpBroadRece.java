package com.zzy.trace.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class udpBroadRece {
	
	public static void main(String[] args) throws SocketException, UnknownHostException {
		new udpBroadRece().start();
	}
	public void start() throws SocketException, UnknownHostException {
		int port = 8999;
		DatagramSocket dsocket = new DatagramSocket(port);
		DatagramPacket drece = new DatagramPacket(new byte[1024], 1024);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0 ; 
				while(true) {
					if(i == 20) {
						dsocket.close();
						break;
					}
					i++;
					try {
						dsocket.receive(drece);
						byte[] data = drece.getData();
						int len = drece.getLength();
						InetAddress addr = drece.getAddress();
						System.out.printf("received: %s, length=%d, address=%s %n",new String(data),10,addr);
						Thread.sleep(1000);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				dsocket.close();
			}
		}).start();
	}
	
	

}
