package com.apress.springrecipes.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import com.apress.springrecipes.shop.BannerLoader;
import com.apress.springrecipes.shop.DiscountFactoryBean;
import com.apress.springrecipes.shop.Product;
import com.apress.springrecipes.shop.ProductCreator;

@Configuration
@ComponentScan("com.apress.springrecipes.shop")
public class ProductConfiguration {
	
	
	@Value("classpath:banner.txt")
	private Resource banner;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer
		propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

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
		factory.setDiscount(0.1);
		return factory;
	}
	
	@Bean
	public DiscountFactoryBean discountFactoryBeanCDRW() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(cdrw());
		factory.setDiscount(0.5);
		return factory;
	}
	
	@Bean
	public DiscountFactoryBean discountFactoryBeanDVDRW() {
		DiscountFactoryBean factory = new DiscountFactoryBean();
		factory.setProduct(dvdrw());
		factory.setDiscount(0.7);
		return factory;
	}
	
	@Bean
	public BannerLoader bannerLoader() {
		BannerLoader bl = new BannerLoader();
		bl.setBanner(banner);
		return bl;
	}
}
