package com.apress.springrecipes.post;

import org.springframework.jms.core.support.JmsGatewaySupport;

public class FrontDeskSupportImpl extends JmsGatewaySupport implements FrontDesk {

	@Override
	public void sendMail(Mail mail) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("mailId", mail.getMailId());
//		map.put("country", mail.getCountry());
//		map.put("weight", mail.getWeight());
//		getJmsTemplate().convertAndSend(map);
		getJmsTemplate().convertAndSend(mail);
	}
}
