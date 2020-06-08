package com.apress.springrecipes.calculator;

public class MinCalculatorImpl implements MinCalculator {

	@Override
	public double min(double a, double b) {
		double res = (a >= b) ? b : a;
		System.out.println("min(" + a + ", " + b + ") = " + res);
		return res;
	}

}
