package com.apress.springrecipes.calculator;


import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.shop.Cashier;
import com.apress.springrecipes.shop.ShoppingCart;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        UnitCalculator unitCalculator = context.getBean("unitCalculator", UnitCalculator.class);
        unitCalculator.kilogramToPound(10);
        unitCalculator.kilometerToMile(5);
        
        ArithmeticCalculator arithmeticCalculator =
                context.getBean("arithmeticCalculator", ArithmeticCalculator.class);
        arithmeticCalculator.add(1, 2);
        arithmeticCalculator.sub(4, 3);
        arithmeticCalculator.mul(2, 3);
        arithmeticCalculator.div(4, 2);
        
        System.out.println("----------- ordered test ---------");
        arithmeticCalculator.div(1, 0);
        
//        CalculatorTestShow testShow = (CalculatorTestShow) context.getBean("calculatorTestShow");
//        testShow.showAnno();
        
        System.out.println("----------- introduction test ---------");
        MaxCalculator maxCalculator = (MaxCalculator) arithmeticCalculator;
        maxCalculator.max(12, 11.9);
        
        MinCalculator minCalculator = (MinCalculator) arithmeticCalculator;	
        minCalculator.min(12, 11.9);
        
        Counter arithmeticCounter = (Counter) arithmeticCalculator;
        System.out.println("arithmeticCounter -----> " + arithmeticCounter.getCount());
        Counter unitCounter = (Counter) unitCalculator;
        System.out.println("unitCounter -----> " + unitCounter.getCount());
        
        System.out.println("----------- weaver test ---------");
        ComplexCalculator complexCalculator = context.getBean("complexCalculator", ComplexCalculator.class);
        complexCalculator.sub(new Complex(5, 8), new Complex(2,3));
        complexCalculator.add(new Complex(1, 2), new Complex(2,3));
        System.out.println("----------- Event test ---------");
        ApplicationContext eventContext = new AnnotationConfigApplicationContext("com.apress.springrecipes.shop");
        ShoppingCart cart = (ShoppingCart) eventContext.getBean("shoppingCart");
        Cashier cashier = (Cashier) eventContext.getBean("cashier");
        try {
			cashier.checkout(cart);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

