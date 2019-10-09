package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class updRece {
	
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(10005);
		byte[] bytes = new byte[1024];
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
		while(true) {
			ds.receive(dp);
			byte[] data = dp.getData();
			String str = new String(data,0,dp.getLength());
			if("886".equals(str)) {
				break;
			}
			System.out.println("udp server receive: " + str);
		}
		ds.close();
	}
}
