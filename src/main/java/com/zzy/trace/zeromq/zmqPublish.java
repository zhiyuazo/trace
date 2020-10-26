package com.zzy.trace.zeromq;

import java.util.Random;

import org.zeromq.ZMQ;

public class zmqPublish {
	public static void main(String[] args) throws InterruptedException {
		ZMQ.Context  context = ZMQ.context(1);
	    ZMQ.Socket publisher = context.socket(ZMQ.PUB);
	    publisher.bind("tcp://*:5556");
//	    publisher.bind("icp://weather");
	    Random srandom = new Random(System.currentTimeMillis());
	    while (!Thread.currentThread().isInterrupted()) {
	        int zipcode, temperature, relhumidity;
	        zipcode = 10000 + srandom.nextInt(5);
	        temperature = 20 + srandom.nextInt(10) ;
	        relhumidity = 30 + srandom.nextInt(10) ;
	        String update = String.format("%05d %d %d", zipcode, temperature, relhumidity);
	        publisher.send(update.getBytes());
	        System.out.println("publish: " + update);
	        Thread.sleep(1000);
	    }
	    publisher.close();
	    context.term();
	}
}
