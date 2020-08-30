package com.apress.springrecipes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apress.springrecipes.nosql.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
//	long count();
	Vehicle save(Vehicle vehicle);
//	void delete(Vehicle vehicle);
//	List<Vehicle> findAll();
	Vehicle findByVehicleNo(String vehicleNo);
}
