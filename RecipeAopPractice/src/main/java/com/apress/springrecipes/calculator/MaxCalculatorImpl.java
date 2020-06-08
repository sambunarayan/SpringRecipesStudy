package com.apress.springrecipes.calculator;

public class MaxCalculatorImpl implements MaxCalculator {

	@Override
	public double max(double a, double b) {
		double res = (a >= b) ? a : b;
		System.out.println("max(" + a + ", " + b + ") = " + res);
		return res;
	}

}
