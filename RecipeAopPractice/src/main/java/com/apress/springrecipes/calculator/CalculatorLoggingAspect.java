package com.apress.springrecipes.calculator;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CalculatorLoggingAspect {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Before("CalculatorPointcuts.loggingOperation())")
    public void logJoinPoint(JoinPoint joinPoint) {
    	log.info("Join point kind: {}", joinPoint.getKind());
    	log.info("Signature declaring type: {}", joinPoint.getSignature().getDeclaringTypeName());
    	log.info("Signature Name: {}", joinPoint.getSignature().getName());
    	log.info("Arguments: {}", Arrays.toString(joinPoint.getArgs()));
    	log.info("Target Class: {}", joinPoint.getTarget().getClass().getName());
    	log.info("This class : {}", joinPoint.getThis().getClass().getName());
    }

    @Before("CalculatorPointcuts.loggingOperation())")
    public void logBefore(JoinPoint joinPoint) {
        log.info("The method {}() begins with {} ", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
    
    @After("CalculatorPointcuts.loggingOperation())")
    public void logAfter(JoinPoint joinPoint) {
    	log.info("The method " + joinPoint.getSignature().getName() + "() ends");
    }

    @AfterReturning(
    		pointcut = "CalculatorPointcuts.loggingOperation())",
    		returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
    	log.info("The method {}() ends with {}", joinPoint.getSignature().getName(), result);
    }
    
    @AfterThrowing(
    		pointcut = "CalculatorPointcuts.loggingOperation())",
    		throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    	log.error("An exception {} has been thrown in {}()", e, joinPoint.getSignature().getName());
    }
    
    @Around("execution(* ArithmeticCalculator.div(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) {
    	log.info("The method {}() begins with {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    	try {
    		Object result = joinPoint.proceed();
    		log.info("The method {}() ends with ", joinPoint.getSignature().getName(), result);
    		return result;
    	} catch (Throwable e) {
    		log.error("An exception {} has been thrown in {}()", e, joinPoint.getSignature().getName());
    		return Double.parseDouble("0");
    	}
    }
}

