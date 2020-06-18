package com.apress.springrecipes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ExceptionHandlerConfiguration implements WebMvcConfigurer {
	
//	@Override
//	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//		exceptionResolvers.add(handlerExceptionResolver());
//	}
	
//	@Bean
//	public HandlerExceptionResolver handlerExceptionResolver() {
//		Properties exceptionMapping = new Properties();
//		exceptionMapping.setProperty(
//				ReservationNotAvailableException.class.getName(), "reservationNotAvailable");
//		
//		SimpleMappingExceptionResolver exceptionResolver =
//				new SimpleMappingExceptionResolver();
//		exceptionResolver.setExceptionMappings(exceptionMapping);
//		exceptionResolver.setDefaultErrorView("error");
//		return exceptionResolver;
//	}
}
