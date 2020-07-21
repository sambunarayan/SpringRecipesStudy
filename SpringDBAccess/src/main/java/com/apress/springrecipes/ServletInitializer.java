package com.apress.springrecipes;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.apress.springrecipes.config.RootConfig;


public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer	 {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {RootConfig.class};
	}


	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}
