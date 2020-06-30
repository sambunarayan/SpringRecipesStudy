package com.apress.springrecipes.echo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	
	@Bean
	public ServletServerContainerFactoryBean configureWebSocketContainer() {
		ServletServerContainerFactoryBean factory = new ServletServerContainerFactoryBean();
		factory.setMaxBinaryMessageBufferSize(16384);
		return factory;
	}
	
	@Bean
	public EchoHandler echoHandler() {
		return new EchoHandler();
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(echoHandler(), "/echo").addInterceptors();
	}
}
