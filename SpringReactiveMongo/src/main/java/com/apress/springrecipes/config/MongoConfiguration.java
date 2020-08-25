package com.apress.springrecipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.apress.springrecipes.repositories.MongoDBVehicleRepository;
import com.apress.springrecipes.repositories.VehicleRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.apress.springrecipes.nosql")
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {
	
	public static final String DB_NAME = "vehicledb";
	
	@Bean
	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create();
	}

	@Override
	protected String getDatabaseName() {
		return DB_NAME;
	}
		
	@Bean
	public VehicleRepository vehicleRepository(ReactiveMongoTemplate mongoTemplate) {
		return new MongoDBVehicleRepository(mongoTemplate);
	}
}
