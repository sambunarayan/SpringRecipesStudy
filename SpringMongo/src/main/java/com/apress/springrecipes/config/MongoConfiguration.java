package com.apress.springrecipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.apress.springrecipes.repositories.MongoDBVehicleRepository;
import com.apress.springrecipes.repositories.VehicleRepository;
import com.mongodb.client.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.apress.springrecipes.nosql")
@ComponentScan("com.apress.springrecipes.repositories")
public class MongoConfiguration {

	public static final String DB_NAME = "vehicledb";
	
	@Bean
	public MongoTemplate mongo(MongoClient mongo) throws Exception {
		return new MongoTemplate(mongo, DB_NAME);
	}
	
	@Bean
	public MongoClientFactoryBean mongoFactoryBean() {
		return new MongoClientFactoryBean();
	}
	
	@Bean
	public VehicleRepository vehicleRepository(MongoTemplate mongo) {
		return new MongoDBVehicleRepository(mongo);
	}
}
