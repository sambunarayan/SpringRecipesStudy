package com.apress.springrecipes.converter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.apress.springrecipes.post.Mail;

public class MailMessageConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		Mail mail = (Mail) object;
		MapMessage message = session.createMapMessage();
		message.setString("mailId", mail.getMailId());
		message.setString("country", mail.getCountry());
		message.setDouble("weight", mail.getWeight());
		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		MapMessage mapMessage = (MapMessage) message;
		Mail mail = new Mail();
		mail.setMailId(mapMessage.getString("mailId"));
		mail.setCountry(mapMessage.getString("country"));
		mail.setWeight(mapMessage.getDouble("weight"));
		return mail;
	}

}
