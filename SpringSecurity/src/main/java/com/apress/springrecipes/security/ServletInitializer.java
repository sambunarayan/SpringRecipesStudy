package com.apress.springrecipes.security;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.springrecipes.security.config.TodoSecurityConfig;

public class ServletInitializer extends AbstractSecurityWebApplicationInitializer implements ServletContainerInitializer {
	
	public ServletInitializer() {
		super(TodoSecurityConfig.class);
	}
	
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(new Class[] { TodoSecurityConfig.class});

		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		ServletRegistration.Dynamic courtRegistration = ctx.addServlet("dispatcher", dispatcherServlet);
		courtRegistration.setLoadOnStartup(1);
		courtRegistration.addMapping("/");
		courtRegistration.setAsyncSupported(true);
		
	}

}
