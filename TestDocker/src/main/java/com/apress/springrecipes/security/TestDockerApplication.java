package com.apress.springrecipes.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDockerApplication.class, args);
	}
	
	@RequestMapping(value="/")
	String sayHello() {
		return "Hello World!";
	}

}
