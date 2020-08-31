package com.apress.springrecipes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.config.BackOfficeConfiguration;
import com.apress.springrecipes.post.BackOffice;
import com.apress.springrecipes.post.Mail;

public class BackOfficeMain {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BackOfficeConfiguration.class);

		BackOffice backOffice = context.getBean(BackOffice.class);
		Mail mail = backOffice.receiveMail();
		System.out.println("Mail #" + mail.getMailId() + " received");
	}
}
