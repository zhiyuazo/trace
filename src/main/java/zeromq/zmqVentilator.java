package zeromq;

import java.io.IOException;
import java.util.Random;

import org.zeromq.ZMQ;

public class zmqVentilator {
    public static void main(String[] args) throws IOException, InterruptedException {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket push_src = context.socket(ZMQ.PUSH);
        push_src.bind("tcp://*:5557");
        Random srandom = new Random(System.currentTimeMillis());
        int task_nbr;
        for (task_nbr = 0; task_nbr < 100; task_nbr++) {
        	String task = "taskTsFusion-" + task_nbr;
            System.out.println("task-center distribute: " + task);
            push_src.send(task, 0);
            Thread.sleep(1000);
        }
        push_src.close();
        context.term();
    }
}
