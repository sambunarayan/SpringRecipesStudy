package com.apress.springrecipes.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import com.apress.springrecipes.converter.MailMessageConverter;
import com.apress.springrecipes.post.BackOfficeSupportImpl;
import com.apress.springrecipes.post.FrontDeskSupportImpl;

@Configuration
public class JmsConfiguration {
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
		jmsTemplate.setMessageConverter(messageConverter());
		return jmsTemplate;
	}
	
	@Bean
	public MessageConverter messageConverter() {
		return new MailMessageConverter();
	}
	
	@Bean
	public BackOfficeSupportImpl backOffice() {
		BackOfficeSupportImpl backOffice = new BackOfficeSupportImpl();
		backOffice.setJmsTemplate(jmsTemplate());
		return backOffice;
	}
	
	@Bean
	public FrontDeskSupportImpl frontDesk() {
		FrontDeskSupportImpl frontDesk = new FrontDeskSupportImpl();
		frontDesk.setJmsTemplate(jmsTemplate());
		return frontDesk;
	}
}
