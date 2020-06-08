package com.apress.springrecipes.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:discount.properties")
public abstract class Product {
	
	private String name;
	private double price;
	private double discount;
	
	@Value("${endofyear.discount:0.0}")
	private double endofyear;

	public Product() {}
	
	public Product(String name, double price, double discount) {
		this.name = name;
		this.price = price;
		this.discount = discount;
	}
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public String toString() {
		return name + " " + price + " " + endofyear;
	}
}
