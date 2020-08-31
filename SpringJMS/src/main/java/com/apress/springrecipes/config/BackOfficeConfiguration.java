package com.apress.springrecipes.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.apress.springrecipes.post.BackOfficeJmsImpl;
import com.apress.springrecipes.post.FrontDeskJmsImpl;

@Configuration
public class BackOfficeConfiguration {
	
	@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}
	
	@Bean
	public Queue destination() {
		return new ActiveMQQueue("mail.queue");
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setReceiveTimeout(10000);
		jmsTemplate.setDefaultDestination(destination());
		return jmsTemplate;
	}
	
	@Bean
	public BackOfficeJmsImpl backOffice() {
		BackOfficeJmsImpl backOffice = new BackOfficeJmsImpl();
		backOffice.setJmsTemplate(jmsTemplate());
		return backOffice;
	}
	
	@Bean
	public FrontDeskJmsImpl frontDesk() {
		FrontDeskJmsImpl frontDesk = new FrontDeskJmsImpl();
		frontDesk.setJmsTemplate(jmsTemplate());
		return frontDesk;
	}

//	@Bean
//	public BackOfficeImpl backOffice() {
//		return new BackOfficeImpl();
//	}
}
