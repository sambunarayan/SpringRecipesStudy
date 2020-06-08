package com.apress.springrecipes.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import com.apress.springrecipes.shop.DiscountFactoryBean;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.ProductCreator;

@Configuration
@Profile("global")
public class ProductConfigurationGlobal {
	@Bean
	@DependsOn("propertySourcesPlaceholderConfigurer")
	public Product aaa() {
		return ProductCreator.createProduct("aaa");
	}

	@Bean
	@DependsOn("propertySourcesPlaceholderConfigurer")
	public Product cdrw() {
		return ProductCreator.createProduct("cdrw");
	}

	@Bean
	@DependsOn("propertySourcesPlaceholderConfigurer")
	public Product dvdrw() {
		return ProductCreator.createProduct("dvdrw");
	}

	@Bean
	public DiscountFactoryBean discountFactoryBeanAAA() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(aaa());
		factory.setDiscount(0.2);
		return factory;
	}

	@Bean
	public DiscountFactoryBean discountFactoryBeanCDRW() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(cdrw());
		factory.setDiscount(0.4);
		return factory;
	}

	@Bean
	public DiscountFactoryBean discountFactoryBeanDVDRW() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(dvdrw());
		factory.setDiscount(0.6);
		return factory;
	}
}
