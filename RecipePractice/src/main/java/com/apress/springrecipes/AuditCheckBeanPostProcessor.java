package com.apress.springrecipes;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.apress.springrecipes.shop.Product;

@Component
public class AuditCheckBeanPostProcessor implements BeanPostProcessor {
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		System.out.println("In AuditCheckBeanPostProcessor.postPrecessBeforeInitialization,"
				+ "processing bean type:" + bean.getClass());
		
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		if (bean instanceof Product) {
			System.out.println("In AuditCheckBeanPostProcessor.postProcessAfterInitialization,"
					+ "processing bean type:" + bean.getClass());
		}
		
		return bean;
	}
}
