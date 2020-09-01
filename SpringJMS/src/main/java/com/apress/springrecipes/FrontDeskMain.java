package com.apress.springrecipes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.config.FrontOfficeConfiguration;
import com.apress.springrecipes.config.JmsConfiguration;
import com.apress.springrecipes.post.FrontDesk;
import com.apress.springrecipes.post.Mail;

public class FrontDeskMain {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(JmsConfiguration.class);
		
		FrontDesk frontDesk = context.getBean(FrontDesk.class);
		frontDesk.sendMail(new Mail("1234", "kr", 1.5));
		
	}
}
