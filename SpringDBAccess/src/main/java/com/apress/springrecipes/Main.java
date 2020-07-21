package com.apress.springrecipes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.config.RootConfig;
import com.apress.springrecipes.dao.VehicleDao;
import com.apress.springrecipes.vehicle.Vehicle;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(RootConfig.class);
		
		VehicleDao vehicleDao = context.getBean(VehicleDao.class);
		Vehicle vehicle = new Vehicle("TEM0001", "Red", 4, 4);
		vehicleDao.insert(vehicle);
		
		vehicle = vehicleDao.findByVehicleNo(vehicle.getVehicleNo());
		System.out.println(vehicle);
	}
}
