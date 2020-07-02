package com.apress.springrecipes.reactive;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

import com.apress.springrecipes.reactive.configuration.WebFluxConfiguration;

public class SpringWebFluxInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(WebFluxConfiguration.class);
		HttpHandler handler = WebHttpHandlerBuilder.applicationContext(applicationContext).build();
		ServletHttpHandlerAdapter handlerAdapter = new ServletHttpHandlerAdapter(handler);
		ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher-handler", handlerAdapter);
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
		registration.setAsyncSupported(true);
	}
}
