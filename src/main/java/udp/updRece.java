package udp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;


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
