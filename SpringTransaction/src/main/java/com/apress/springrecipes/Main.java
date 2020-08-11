package com.apress.springrecipes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.bookshop.BookShop;
import com.apress.springrecipes.bookshop.config.BookstoreConfiguration;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BookstoreConfiguration.class);
		BookShop bookShop = context.getBean(BookShop.class);
		bookShop.purchase("0001", "user1");
	}

}
