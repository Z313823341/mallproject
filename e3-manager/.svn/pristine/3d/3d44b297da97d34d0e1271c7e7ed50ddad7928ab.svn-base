package cn.e3mall.activeMq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActiveMQSpring {
	
	@Test
	public void testSendMessage() throws Exception{
		//1.初始化sprng容器
		//2.从shipring容器中 获得jmsTemplate对象
		//3.从spring容器中取Destination
		//4.使用JmsTemplate对象发送消息
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		
		JmsTemplate template = ac.getBean(JmsTemplate.class);
		Destination destination = (Destination) ac.getBean("queueDestination");
		template.send(destination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				
				return session.createTextMessage("send activeMQ message");
			}
		});
	}
	
	

}
