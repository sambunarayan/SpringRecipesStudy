package com.apress.springrecipes.reactive;

import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

import com.apress.springrecipes.reactive.configuration.WebFluxConfiguration;

public class SpringWebFluxInitializer extends AbstractReactiveWebInitializer {

	@Override
	protected Class<?>[] getConfigClasses() {
		return new Class<?>[] { WebFluxConfiguration.class };
	}

}
