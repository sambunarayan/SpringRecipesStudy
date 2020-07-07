package com.apress.springrecipes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.apress.springrecipes.converter.SportTypeConverter;
import com.apress.springrecipes.service.ReservationService;

@Configuration
@EnableWebMvc
@ComponentScan("com.apress.springrecipes.court.web")
public class WebConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private ReservationService reservationService;
	
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new SportTypeConverter(reservationService));
	}
}
