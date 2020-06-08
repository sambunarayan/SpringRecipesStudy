package com.apress.springrecipes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.calculator.ArithmeticCalculator;
import com.apress.springrecipes.calculator.UnitCalculator;
import com.apress.springrecipes.calculator.config.CalculatorConfiguration;

public class CalMain {
	public static void main(String[] args) {
		System.out.println("----------- AOP ----------");
		ApplicationContext calContext = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) calContext.getBean(ArithmeticCalculator.class);
		arithmeticCalculator.add(1, 2);
		arithmeticCalculator.sub(3, 4);
		arithmeticCalculator.mul(5, 6);
		arithmeticCalculator.div(7, 8);
		
		UnitCalculator unitCalculator = calContext.getBean("unitCalculator", UnitCalculator.class);
		unitCalculator.kilogramToPound(64);
		unitCalculator.kilometerToMile(2);
	}

}
