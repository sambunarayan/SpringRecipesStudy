package com.apress.springrecipes.post;

import org.springframework.jms.core.support.JmsGatewaySupport;

public class BackOfficeSupportImpl extends JmsGatewaySupport implements BackOffice {

	@Override
	public Mail receiveMail() {
//		Map map = (Map) getJmsTemplate().receiveAndConvert();
//		Mail mail = new Mail();
//		mail.setMailId((String)map.get("mailId"));
//		mail.setCountry((String)map.get("country"));
//		mail.setWeight((Double)map.get("weight"));
//		return mail;
		return (Mail) getJmsTemplate().receiveAndConvert();
	}

}
