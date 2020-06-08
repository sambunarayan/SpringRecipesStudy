package com.apress.springrecipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.apress.springrecipes.shop.Cashier;

@Configuration
public class ShopConfiguration {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}
	
	@Bean(initMethod = "openFile", destroyMethod = "closeFile")
	public Cashier cashier() {
		System.out.println("tmpdir ---------------> " + System.getProperty("java.io.tmpdir"));
		String path = System.getProperty("java.io.tmpdir") + "/cashier";
		Cashier c1 = new Cashier();
		c1.setFile("checkout");
		c1.setPath(path);
		return c1;
	}
}
