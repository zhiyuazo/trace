package com.zzy.trace.zeromq;

import org.zeromq.ZMQ;

public class zmqSink {
    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket pull4Sink = context.socket(ZMQ.PULL);
        pull4Sink.bind("tcp://*:5558");
        while (!Thread.currentThread().isInterrupted()) {
            String recv = new String(pull4Sink.recv(0)).trim();
            System.out.println("Sink Receive: " + recv);
        }
        pull4Sink.close();
        context.term();
    }
}
