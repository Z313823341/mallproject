package cn.e3mall.activeMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.memory.buffer.MessageQueue;
import org.junit.Test;
/*
 * 点到点形式发送消息
 */
public class TestActiveProducer {
	
	@Test
	public void testActiveProducer() throws Exception{
		//1.创建一个连接工厂对象，需要指定服务的ip及端口
		//2.使用工厂对象床创建一个Connection连接
		//3.开启连接，调用Connection对象的start的方法
		//4.创建一个session对象
		//5.使用session创建一个Destination对象，目的地，有两种形式，quene和topic
		//6.使用session创建一个Producer对象
		//7.创建一个Message对象，可以使用TestMessage
		//8.发送消息
		//9.关闭资源
		
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		
		Connection connection = factory.createConnection();
		
		connection.start();
		
		//第一个参数：是否开启失误，一般不开启失误 如果开启事务，第二个参数无意义
		//第二个参数：应答模式，一般是两种魔兽，手动应答，自动应答
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		
		Queue queue = session.createQueue("test-queue");
		
		MessageProducer producer = session.createProducer(queue);
		
	 	TextMessage message = new ActiveMQTextMessage();
		
		message.setText("hello Active");
		
		producer.send(message);
		
		producer.close();
		session.close();
		connection.close();
	}
	
	@Test
	public void test2() throws Exception{
		       //1.创建一个连接工厂对象，需要指定服务的ip及端口
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
				//2.使用工厂对象床创建一个Connection连接
		Connection connection = factory.createConnection();
				//3.开启连接，调用Connection对象的start的方法
		connection.start();
				//4.创建一个session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				//5.使用session创建一个Destination对象，目的地，有两种形式，quene和topic
		Queue queue = session.createQueue("summer1");
		       //6.使用session创建一个Producer对象
		MessageProducer producer = session.createProducer(queue);
				//7.创建一个Message对象，可以使用TestMessage
		TextMessage message = new ActiveMQTextMessage();
		message.setText("summerhuang");
				//8.发送消息
		producer.send(message);
				//9.关闭资源
		producer.close();
		session.close();
		connection.close();
		
	}
	//Consumer
	@Test
	public void testQueueConsume() throws Exception{
		//创建一个ConnectionFactory对象
		//创建一个Connection
		//开启连接
		//创建一个Session
		//创建一个Dsetination对象
		//创建消费者对象
		//接收消息
		//关闭连接
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//参数是消息的名字
		Queue queue = session.createQueue("test-queue");
		MessageConsumer consumer = session.createConsumer(queue);
		/*Message message = consumer.receive();
		System.out.println(message);*/
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage)message;
				String text;
				try {
					text =  textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
	}

}
