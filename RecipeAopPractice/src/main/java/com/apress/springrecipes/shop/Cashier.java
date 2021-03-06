package com.apress.springrecipes.shop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.apress.springrecipes.shop.event.CheckoutEvent;

@Component
public class Cashier {
	
	@Value("checkout")
	private String fileName;
	@Value("C:\\Users\\soyou\\AppData\\Local\\Temp\\cashier")
	private String path;
	private BufferedWriter writer;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	public void setFile(String fileName) {
		this.fileName = fileName;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@PostConstruct
	public void openFile() throws IOException {
		
		File targetDir = new File(path);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		
		File checkoutFile = new File(path, fileName + ".txt");
		if (!checkoutFile.exists()) {
			checkoutFile.createNewFile();
		}
		
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(checkoutFile, true)));
	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public void checkout(ShoppingCart cart) throws IOException {
		String alert = messageSource.getMessage("alert.inventory.checkout", new Object[] {cart.getItems(), new Date()}, Locale.JAPAN);
		System.out.println(alert);
		
		writer.write(new Date() + "\t" + cart.getItems() + "\r\n");
		writer.flush();
		
		CheckoutEvent event = new CheckoutEvent(cart, new Date());
		applicationEventPublisher.publishEvent(event);
	}
	
	@PreDestroy
	public void closeFile() throws IOException {
		System.out.println("file close!!!!!");
		writer.close();
	}
}
