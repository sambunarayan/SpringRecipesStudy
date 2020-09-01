package com.apress.springrecipes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.apress.springrecipes.post.BackOfficeJmsImpl;

@Configuration
public class BackOfficeConfiguration {
	
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		return new ActiveMQConnectionFactory("tcp://localhost:61616");
//	}
//	
//	@Bean
//	public Queue destination() {
//		return new ActiveMQQueue("mail.queue");
//	}
//	
//	@Bean
//	public JmsTemplate jmsTemplate() {
//		JmsTemplate jmsTemplate = new JmsTemplate();
//		jmsTemplate.setConnectionFactory(connectionFactory());
//		jmsTemplate.setReceiveTimeout(10000);
//		jmsTemplate.setDefaultDestination(destination());
//		return jmsTemplate;
//	}
//	
//	@Bean
//	public BackOfficeJmsImpl backOffice() {
//		BackOfficeJmsImpl backOffice = new BackOfficeJmsImpl();
//		backOffice.setJmsTemplate(jmsTemplate());
//		return backOffice;
//	}
//	
//	@Bean
//	public BackOfficeImpl backOffice() {
//		return new BackOfficeImpl();
//	}
}
