package com.apress.springrecipes.web;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.springrecipes.config.CourtConfiguration;
import com.apress.springrecipes.config.CourtRestConfiguration;
import com.apress.springrecipes.config.ExceptionHandlerConfiguration;
import com.apress.springrecipes.config.InterceptorConfiguration;
import com.apress.springrecipes.config.ViewResolverConfiguration;
import com.apress.springrecipes.config.WebConfiguration;

public class CourtServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(new Class[] { CourtConfiguration.class, InterceptorConfiguration.class,
				ViewResolverConfiguration.class, ExceptionHandlerConfiguration.class, WebConfiguration.class,
				CourtRestConfiguration.class});

		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		ServletRegistration.Dynamic courtRegistration = ctx.addServlet("court", dispatcherServlet);
		courtRegistration.setLoadOnStartup(1);
		courtRegistration.addMapping("/");
	}

}
