package com.apress.springrecipes.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer, ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		Map<String, MediaType> mediatypes = new HashMap<>();
		mediatypes.put("html", MediaType.TEXT_HTML);
		mediatypes.put("pdf", MediaType.valueOf("application/pdf"));
		mediatypes.put("xls", MediaType.valueOf("application/vnd.ms-excel"));
		mediatypes.put("xml", MediaType.APPLICATION_XML);
		mediatypes.put("json", MediaType.APPLICATION_JSON);
		configurer.mediaTypes(mediatypes);
	}

	@Bean
	public ViewResolver viewResolver() {
		XmlViewResolver viewResolver = new XmlViewResolver();
		viewResolver.setLocation(resourceLoader.getResource("/WEB-INF/court-views.xml"));

//		ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
//		viewResolver.setBasename("court-views");
		viewResolver.setOrder(1);
		return viewResolver;
	}	
	
	@Bean
	public ContentNegotiationManager contentNegotiationManager() {
		ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager();
		return contentNegotiationManager;
	}

	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(
			ContentNegotiationManager contentNegotiationManager) {
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		viewResolver.setContentNegotiationManager(contentNegotiationManager);
		viewResolver.setOrder(0);
		return viewResolver;
	}

}
