package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class udpGrpRece {
	
	public static void main(String[] args) throws IOException {
		new udpGrpRece().start();
	}
	public void start() throws IOException  {
		InetAddress grpAddr = InetAddress.getByName("224.0.0.1");
		int port = 8998;
		MulticastSocket msocket = new MulticastSocket(port);
		msocket.joinGroup(grpAddr);
		DatagramPacket drece = new DatagramPacket(new byte[1024], 1024);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0 ; 
				while(true) {
					if(i == 20) {
						msocket.close();
						break;
					}
					i++;
					try {
						msocket.receive(drece);
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
				msocket.close();
			}
		}).start();
	}
	
	

}
