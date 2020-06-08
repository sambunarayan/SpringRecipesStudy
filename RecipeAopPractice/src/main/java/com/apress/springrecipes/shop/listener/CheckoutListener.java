package com.apress.springrecipes.shop.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.apress.springrecipes.shop.event.CheckoutEvent;

@Component
public class CheckoutListener {

	@EventListener
	public void onApplicationEvent(CheckoutEvent event) {
		System.out.println("Checkout event [" + event.getDate() + "]");
	}
}
