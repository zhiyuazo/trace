package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class tcpServer2  implements Runnable {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8887);
		int i =  0; 
		while(i<3) {
			Socket s = ss.accept();
			tcpServer2  srunable = new tcpServer2(s);
			Thread  sthread = new Thread(srunable);
			sthread.setName("socket-thread-"+(i+1));
			sthread.start();
		}
		ss.close();
	}

	
	private Socket sv ;
	public tcpServer2(Socket s){
		this.sv = s ;
	}
	
	@Override
	public void run() {
		try {
			InputStream  in = sv.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			OutputStream os = sv.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			while(true) {
				String  msg = dis.readUTF();
				System.out.println("Server receive: " + msg);
				
				String reply = "已收到..." + msg;
				dos.writeUTF(Thread.currentThread().getName() + ":" + reply);
				if(msg.equals("886")) {
					dos.writeUTF("Server not on you!...");
					break;
				}
			}
			dos.close();
			dis.close();
			sv.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
