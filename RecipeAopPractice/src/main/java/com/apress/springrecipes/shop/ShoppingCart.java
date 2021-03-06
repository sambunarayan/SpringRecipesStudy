package com.apress.springrecipes.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
@Lazy
public class ShoppingCart {
	
	private List<Product> items = new ArrayList<>();
	
	public ShoppingCart() {
		System.out.println(">>>>>>>>> Lazy initialize ShoppingCart <<<<<<<<<<<<");
	}
	
	public void addItem(Product item) {
		items.add(item);
	}
	
	public List<Product> getItems() {
		return items;
	}
}
