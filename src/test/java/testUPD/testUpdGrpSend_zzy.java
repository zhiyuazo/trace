package testUPD;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class testUpdGrpSend_zzy {
	public static void main(String[] args) {
		int dstPort = 5004;
		int bufferSize = 10240;
		String grpAddress = "224.192.168.170";

		MulticastSocket  grpSocketSend;
		DatagramPacket packet;
		byte[] msg = "200OK".getBytes();
		try {
			grpSocketSend = new MulticastSocket();
			for (int i = 0; i < 1000; i++) {
				packet = new DatagramPacket(msg, msg.length,InetAddress.getByName(grpAddress),dstPort);
				grpSocketSend.send(packet);
				System.out.println("send: " + new String(msg));
				Thread.sleep(1000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
