package cn.e3mall.consumer;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MsgConsumer {
	
	@Test
	public void testConsumer() throws Exception{
		//初始化sping容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		System.in.read();
	}

}
