package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class udpBroadSend {
	
	public static void main(String[] args) throws SocketException, UnknownHostException {
		new udpBroadSend().start();
	}
	public void start() throws SocketException, UnknownHostException {
		InetAddress broadAddr = InetAddress.getByName("255.255.255.0");
		int port = 8999;
		DatagramSocket dsocket = new DatagramSocket();
		DatagramPacket dsend = new DatagramPacket("default".getBytes(), 0);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Start broadcast Send!...");
				int i = 0 ; 
				while(i<100) {
					i++;
					String msg = "broadcast-msg-" + i;
					dsend.setData(msg.getBytes());
					dsend.setLength(msg.getBytes().length);
					dsend.setAddress(broadAddr);
					dsend.setPort(port);
					try {
						dsocket.send(dsend);
//						System.out.println("send: " + msg);
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
