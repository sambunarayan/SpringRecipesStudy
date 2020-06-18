package com.apress.springrecipes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.apress.springrecipes")
public class CourtRestConfiguration {
	
//	@Bean
//	public View membertemplate() {
//		return new MarshallingView(jaxb2Marshaller());
//	}
//	
//	@Bean
//	public Marshaller jaxb2Marshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		marshaller.setClassesToBeBound(Members.class, Member.class);
//		return marshaller;
//	}
//	
//	@Bean
//    public ViewResolver viewResolver() {
//		BeanNameViewResolver viewResolver = new BeanNameViewResolver();
//		viewResolver.setOrder(0);
//        return viewResolver;
//    }
}
