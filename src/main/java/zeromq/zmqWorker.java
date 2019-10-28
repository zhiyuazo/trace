package zeromq;

import org.zeromq.ZMQ;

public class zmqWorker {
    public static void main(String[] args) throws InterruptedException {
    	for (int i = 0; i < 3; i++) {
    		new Thread(new Runnable() {
				@Override
				public void run() {
			        ZMQ.Context context = ZMQ.context(1);
			        ZMQ.Socket pullFromVentilator = context.socket(ZMQ.PULL);
			        pullFromVentilator.connect("tcp://localhost:5557");
			        
			        ZMQ.Socket push2Sink = context.socket(ZMQ.PUSH);
			        push2Sink.connect("tcp://localhost:5558");
			        
			        while (!Thread.currentThread().isInterrupted()) {
			            String recv = new String(pullFromVentilator.recv(0)).trim();
			            System.out.println(Thread.currentThread().getName()+"-receive: " + recv);
			            try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
			            push2Sink.send((Thread.currentThread().getName()+"Worker-done").getBytes(), 0);
			        }
			        push2Sink.close();
			        pullFromVentilator.close();
			        context.term();
				}
    		},"work-"+i).start();
		}
    }
}
