package com.apress.springrecipes.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.apress.springrecipes.calculator.ArithmeticCalculator;
import com.apress.springrecipes.calculator.ArithmeticCalculatorImpl;
import com.apress.springrecipes.calculator.UnitCalculator;
import com.apress.springrecipes.calculator.UnitCalculatorImpl;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class CalculatorConfiguration {

	@Bean
	public ArithmeticCalculator arithmeticCalculator() {
		ArithmeticCalculator cal = new ArithmeticCalculatorImpl();
		return cal;
	}
	
	@Bean
	public UnitCalculator unitCalculator() {
		UnitCalculator cal = new UnitCalculatorImpl();
		return cal;
	}
}
