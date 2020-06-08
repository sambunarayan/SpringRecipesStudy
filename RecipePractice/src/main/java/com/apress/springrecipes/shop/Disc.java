package com.apress.springrecipes.shop;

public class Disc extends Product {
	
	private int capacity;
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Disc() {
		super();
	}
	
	public Disc(String name, double price) {
		super(name, price);
	}
	
	
	public Disc(String name, double price, double discount) {
		super(name, price, discount);
	}
}
