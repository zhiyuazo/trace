package com.zzy.trace.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class udpGrpSend {
	
	public static void main(String[] args) throws IOException {
		new udpGrpSend().start();
	}
	public void start() throws IOException {
		InetAddress grpAddr = InetAddress.getByName("224.0.0.1");
		int port = 8998;
		MulticastSocket msocket = new MulticastSocket();
		DatagramPacket dsend = new DatagramPacket("default".getBytes(), 0);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Start grp Send!...");
				int i = 0 ; 
				while(i<100) {
					i++;
					String msg = "grp-msg-" + i;
					dsend.setData(msg.getBytes());
					dsend.setLength(msg.getBytes().length);
					dsend.setAddress(grpAddr);
					dsend.setPort(port);
					try {
						msocket.send(dsend);
						Thread.sleep(1000);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				msocket.close();
			}
		}).start();
	}
	
	

}
