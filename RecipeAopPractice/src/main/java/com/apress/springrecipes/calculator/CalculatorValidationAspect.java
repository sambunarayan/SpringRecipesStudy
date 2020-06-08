package com.apress.springrecipes.calculator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CalculatorValidationAspect implements Ordered {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* Calculator*.*(..)) && !execution(* ComplexCalculator*.*(..))")
	public void validateBefore(JoinPoint joinPoint) {
		for (Object arg : joinPoint.getArgs()) {
			validate((Double) arg);
		}
	}
	
//	@Before("CalculatorPointcuts.loggingRequired()")
//	public void loggingRequired(JoinPoint joinPoint) {
//		log.info("LogginRequired :::::::::: {}", joinPoint.getSignature().getName());
//	}
	
	private void validate(double a) {
		if (a < 0) {
			System.out.println("CalculatorValidationAspect ::::::: ");
			throw new IllegalArgumentException("Positive numbers only");
		}
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
