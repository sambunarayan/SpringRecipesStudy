package com.apress.springrecipes.calculator;

import org.aspectj.lang.annotation.Pointcut;

public class CalculatorPointcuts {
	
	@Pointcut("execution(* *.*(..))")
	public void loggingOperation() {
		
	}
	
//	@Pointcut("annotaion(com.apress.springrecipes.calculator.LoggingRequired)")
	public void loggingRequired() {
		
	}

}
