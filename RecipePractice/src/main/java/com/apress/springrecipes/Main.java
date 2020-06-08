package com.apress.springrecipes;

import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.apress.springrecipes.calculator.ArithmeticCalculator;
import com.apress.springrecipes.calculator.ArithmeticCalculatorImpl;
import com.apress.springrecipes.calculator.UnitCalculator;
import com.apress.springrecipes.calculator.config.CalculatorConfiguration;
import com.apress.springrecipes.dao.SequenceDao;
import com.apress.springrecipes.sequence.SequenceGenerator;
import com.apress.springrecipes.sequence.service.SequenceService;
import com.apress.springrecipes.shop.BannerLoader;
import com.apress.springrecipes.shop.Battery;
import com.apress.springrecipes.shop.Cashier;
import com.apress.springrecipes.shop.Disc;
import com.apress.springrecipes.shop.ShoppingCart;
import com.apress.springrecipes.shop.config.ShopConfiguration;

public class Main {
	public static void main(String[] args) throws Exception {
//		ApplicationContext context = new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);
		ApplicationContext context = new AnnotationConfigApplicationContext("com.apress.springrecipes");
		SequenceGenerator generator = (SequenceGenerator) context.getBean("sequenceGenerator");
		
		System.out.println(generator.getSequence());
		System.out.println(generator.getSequence());
		generator.printPrefix();
		
		SequenceDao seqDao = (SequenceDao) context.getBean("sequenceDao");
		System.out.println(seqDao.getSequence("IT"));
		System.out.println(seqDao.getNextValue("IT"));
		System.out.println(seqDao.getNextValue("IT"));
		System.out.println(seqDao.getNextValue("IT"));
		
		SequenceService seqService = (SequenceService) context.getBean("sequenceService");
		System.out.println("SequenceService ---> " + seqService.generate("IT"));
		
		Battery battery = (Battery) context.getBean("aaa");
		Disc disc = (Disc) context.getBean("cdrw");
		System.out.println(battery);
		System.out.println(disc);
		
		ShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
		cart1.addItem(battery);
		cart1.addItem(disc);
		System.out.println("ShoppingCart1 items --> " + cart1.getItems());
		
		ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
		Disc dvdDisc = (Disc) context.getBean("dvdrw");
		cart2.addItem(dvdDisc);
		
		System.out.println("ShoppingCart1 items --> " + cart1.getItems());
		System.out.println("ShoppingCart2 items --> " + cart2.getItems());
		
		System.out.println();
		BannerLoader bl = context.getBean("bannerLoader", BannerLoader.class);
		
		Resource resource = new ClassPathResource("discount.properties");
//		Resource resource = new FileSystemResource("D:\\workspace\\SpringRecipeWorkspace\\RecipePractice\\src\\main\\resources\\discount.properties");
//		Resource resource = new UrlResource("http://www.apress.com/");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		
		System.out.println("And dont't forget our discounts!");
		System.out.println(props);
		
		System.out.println("----------- MessageBundle ----------");
		ApplicationContext messageContext = new AnnotationConfigApplicationContext(ShopConfiguration.class);
		
		System.out.println(Locale.KOREA);
		String alert = context.getMessage("alert.checkout", null, Locale.JAPAN);
		String alert_inventory = context.getMessage("alert.inventory.checkout", new Object[] {"[DVD-RW 3.0]", new Date()}, Locale.JAPAN);
		
		System.out.println("The I18N message for alert.checkout is: " + alert);
		System.out.println("The I18N message for alert.inventory.checkout is: " + alert_inventory);
		
		String alert_kr = context.getMessage("alert.checkout", null, Locale.KOREA);
		String alsert_inventory_kr = context.getMessage("alert.inventory.checkout", new Object[] {"[Spring Book]", new Date()},Locale.KOREA);
		System.out.println("The I18N message for alert.checkout is: " + alert_kr);
		System.out.println("The I18N message for alert.inventory.checkout is: " + alsert_inventory_kr);
		
		Cashier cashier = (Cashier) context.getBean("cashier");
		cashier.checkout(cart2);
		System.out.println("----------- ----------");
		
		System.out.println("battery ----> " + context.getBean("aaa"));
		System.out.println("CD-RW ----> " + context.getBean("cdrw"));
		System.out.println("DVD-RW ----> " + context.getBean("dvdrw"));
		
		System.out.println("----------- Profile(global) ----------");
		AnnotationConfigApplicationContext globalContext = new AnnotationConfigApplicationContext();
		globalContext.getEnvironment().setActiveProfiles("global");
		globalContext.scan("com.apress.springrecipes.shop");
		globalContext.refresh();
		System.out.println("battery ----> " + globalContext.getBean("aaa"));
		System.out.println("CD-RW ----> " + globalContext.getBean("cdrw"));
		System.out.println("DVD-RW ----> " + globalContext.getBean("dvdrw"));
		
		System.out.println("----------- Profile(summer) ----------");
		AnnotationConfigApplicationContext summerContext = new AnnotationConfigApplicationContext();
		summerContext.getEnvironment().setActiveProfiles("summer", "winter");
		summerContext.scan("com.apress.springrecipes.shop");
		summerContext.refresh();
		
		System.out.println("battery ----> " + summerContext.getBean("aaa"));
		System.out.println("CD-RW ----> " + summerContext.getBean("cdrw"));
		System.out.println("DVD-RW ----> " + summerContext.getBean("dvdrw"));
		
	}
}
