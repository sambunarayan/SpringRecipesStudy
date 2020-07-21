package com.apress.springrecipes.dao;

import java.util.List;

import com.apress.springrecipes.vehicle.Vehicle;

public interface VehicleDao {
	void insert(Vehicle vehicle);
	void insert(Iterable<Vehicle> vehicle);
	void update(Vehicle vehicle);
	void delete(Vehicle vehicle);
	Vehicle findByVehicleNo(String vehicleNo);
	List<Vehicle> findAll();
}
