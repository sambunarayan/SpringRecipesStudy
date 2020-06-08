package com.apress.springrecipes.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorLoggingAspect {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Before("execution(* *.add(..))")
	public void logBefore() {
		log.info("The method add() begins"); 
	}
}
