package com.apress.springrecipes;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.nosql.Vehicle;
import com.apress.springrecipes.repositories.VehicleRepository;
import com.mongodb.MongoClient;

public class SpringMongoApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("com.apress.springrecipes.config");
		VehicleRepository repository = context.getBean(VehicleRepository.class);
		
		System.out.println(repository.count());
		
		repository.save(new Vehicle("1", "TEMP0001", "RED", 4, 4));
		repository.save(new Vehicle("2", "TEMP0002", "RED", 4, 4));
		
		System.out.println(repository.count());
		
		Vehicle v = repository.findByVehicleNo("TEMP0001");
		
		System.out.println(v);
		
		List<Vehicle> vehicleList = repository.findAll();
		
		System.out.println("Number of Vehicles: " + vehicleList.size());
		vehicleList.forEach(System.out::println);
		System.out.println("Number of Vehicles: " + repository.count());
		
		MongoClient mongo = new MongoClient();
		mongo.dropDatabase("vehicles");
		mongo.close();
	}
}
