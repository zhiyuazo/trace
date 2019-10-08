package tcp;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class tcpClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		final Socket s = new Socket("127.0.0.1",8887);
		
		OutputStream os = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		InputStream is = s.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		for (int j = 0; j < 50; j++) {
			String client_str = "message-" + j;
			dos.writeUTF(client_str);
			String server_str = dis.readUTF();
			System.out.println("Receive: " + server_str);
			
			if(client_str.equals("886")) {
				System.out.println(server_str);
				break;
			}
		}
//		Scanner sc = new Scanner(System.in);
//		while(sc.hasNext()) {
//			String client_str = sc.next();
//			dos.writeUTF(client_str);
//			
//			String server_str = dis.readUTF();
//			System.out.print("Receive: " + server_str);
//			
//			if(client_str.equals("886")) {
//				System.out.println(server_str);
//				break;
//			}
//			
//		}
	}
}
