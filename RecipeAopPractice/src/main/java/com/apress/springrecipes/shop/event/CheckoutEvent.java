package com.apress.springrecipes.shop.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.apress.springrecipes.shop.ShoppingCart;

public class CheckoutEvent {

	private static final long serialVersionUID = 7975286897615094252L;
	private final ShoppingCart cart;
	private final Date time;
	
	public CheckoutEvent(ShoppingCart cart, Date time) {
		this.cart = cart;
		this.time = time;
	}
	
	public ShoppingCart getCart() {
		return cart;
	}

	public Date getDate() {
		return time;
	}
}
