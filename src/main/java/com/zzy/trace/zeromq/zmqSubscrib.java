package com.zzy.trace.zeromq;

import java.util.StringTokenizer;

import org.zeromq.ZMQ;

public class zmqSubscrib {
    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket suscriber = context.socket(ZMQ.SUB);
        suscriber.connect("tcp://localhost:5556");
        String filter1 = "10001";
        String filter2 = "10002";
        suscriber.subscribe(filter1.getBytes());  //过滤条件1
        suscriber.subscribe(filter2.getBytes());  //过滤条件2
        int update_nbr;
        long total_temp = 0;
        for (update_nbr = 0; update_nbr < 10; update_nbr++) {
            String rec = suscriber.recvStr(0).trim();
	        System.out.println("receive: " + rec);
            StringTokenizer sscanf = new StringTokenizer(rec, " ");
            int zipcode = Integer.valueOf(sscanf.nextToken());
            int temperature = Integer.valueOf(sscanf.nextToken());
            int relhumidity = Integer.valueOf(sscanf.nextToken());
            total_temp += temperature;
        }
        System.out.println("Average temperature for zipcode '" + filter1 + "' was " + (int) (total_temp / update_nbr));
        suscriber.close();
        context.term();
    }
}
