package com.zzy.trace.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class updSend {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		DatagramSocket ds = new DatagramSocket();
		
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		String line = null; 
		while((line = br.readLine()) != null) {
			byte[] bytes = line.getBytes();
			DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"),10005);
			ds.send(dp);
			if("886".equals(line)) {
				break;
			}
		}
		ds.close();
	}
}
