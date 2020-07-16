package com.apress.springrecipes.mobile;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.apress.springrecipes.mobile.config.MobileConfig;
import com.apress.springrecipes.mobile.filter.DeviceResolverRequestFilter;

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer	 {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {MobileConfig.class};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DeviceResolverRequestFilter()};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
