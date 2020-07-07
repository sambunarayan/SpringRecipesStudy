package com.apress.springrecipes.utils;

import java.util.Random;

public class Delayer {
	private static final Random rnd = new Random();
	
	private Delayer() {}
	
	public static void delay(long delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {}
	}
	
	public static void randomDelay() {
		int delay = rnd.nextInt(5500);
		delay(delay);
	}
}
