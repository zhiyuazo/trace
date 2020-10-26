package com.zzy.trace.activemq;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import cn.hutool.core.util.RandomUtil;

public class topicConsumer {
    //服务地址，端口默认61616
    private static final String url="tcp://127.0.0.1:61616";
    //这次消费的消息名称
    private static final String topicName="topic_style";
    public static void main(String[] args) throws JMSException {
	   String consumerName;
    	for (int i = 0; i < 3; i++) {
    		consumerName = "consumer-topic-" + i; 
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
				        //0. 先判断端口是否启动了 Active MQ 服务器
				        ActiveMQUtil.checkServer();
				        System.out.printf("%s 消费者启动了。 %n",Thread.currentThread().getName()); 
				 
				        //1.创建ConnectiongFactory,绑定地址
				        ConnectionFactory factory=new ActiveMQConnectionFactory(url);
				        //2.创建Connection
				        Connection connection= factory.createConnection();
				        //3.启动连接
				        connection.start();
				        //4.创建会话
				        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				        //5.创建一个目标 （队列类型）
				        Destination destination=session.createTopic(topicName);
				        //6.创建一个消费者
				        MessageConsumer consumer=session.createConsumer(destination);
				        //7.创建一个监听器
				        consumer.setMessageListener(new MessageListener() {
				            public void onMessage(Message arg0) {
				                TextMessage textMessage=(TextMessage)arg0;
				                try {
				                    System.out.println(Thread.currentThread().getName() +" 接收消息："+textMessage.getText());
				                } catch (JMSException e) {
				                    e.printStackTrace();
				                }
				 
				            }
				        });
				        //8. 因为不知道什么时候有，所以没法主动关闭，就不关闭了，一直处于监听状态
				        //connection.close();
					}catch(Exception e) {
						System.out.println("Exception Occured...");
					}
				}
			},consumerName).start();
		}
    }
}
