package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class tcpServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8886);
		Thread.currentThread().setName("Socket Server Single..");
		
		Socket s = ss.accept();
		
		InputStream  in = s.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		OutputStream os = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		while(true) {
			String  msg = dis.readUTF();
			System.out.println("Server receive: " + msg);
			
			String reply = "已收到...";
			dos.writeUTF(reply);
			if(msg.equals("886")) {
				dos.writeUTF("Server not on you!...");
				break;
			}
		}
		dos.close();
		dis.close();
		s.close();
		ss.close();
		
	}
}
