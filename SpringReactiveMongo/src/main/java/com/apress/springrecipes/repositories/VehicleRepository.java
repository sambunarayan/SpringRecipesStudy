package com.apress.springrecipes.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.apress.springrecipes.nosql.Vehicle;

import reactor.core.publisher.Mono;

public interface VehicleRepository extends ReactiveMongoRepository<Vehicle, String> {
	Mono<Vehicle> findByVehicleNo(String vehicleNo);
}
