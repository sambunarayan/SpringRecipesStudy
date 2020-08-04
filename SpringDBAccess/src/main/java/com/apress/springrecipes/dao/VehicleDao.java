package com.apress.springrecipes.dao;

import java.util.Collection;
import java.util.List;

import com.apress.springrecipes.vehicle.Vehicle;

public interface VehicleDao {
	void insert(Vehicle vehicle);
	void insert(Collection<Vehicle> vehicle);
	void update(Vehicle vehicle);
	void delete(Vehicle vehicle);
	void truncate();
	String getColor(String vehicleNo);
	int countAll();
	Vehicle findByVehicleNo(String vehicleNo);
	List<Vehicle> findAll();
}
