package com.apress.springrecipes;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.config.RootConfig;
import com.apress.springrecipes.dao.JdbcVehicleDao;
import com.apress.springrecipes.dao.VehicleDao;
import com.apress.springrecipes.vehicle.Vehicle;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);

		VehicleDao vehicleDao = context.getBean(VehicleDao.class);

		vehicleDao.truncate();

		Vehicle vehicle = new Vehicle("TEM0001", "Red", 4, 4);
		vehicleDao.insert(vehicle);

		vehicle = vehicleDao.findByVehicleNo(vehicle.getVehicleNo());
		System.out.println(vehicle);

		vehicle = new Vehicle("TEM0002", "Blue", 3, 2);
		VehicleDao jdbcVehicleDao = new JdbcVehicleDao(context.getBean(DataSource.class));
		jdbcVehicleDao.insert(vehicle);
		vehicle.setColor("Yellow");
		vehicle.setSeat(11);
		jdbcVehicleDao.update(vehicle);

		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Vehicle("TEM0003", "BLACK", 1, 2));
		vehicles.add(new Vehicle("TEM0004", "PINK", 3, 4));
		vehicles.add(new Vehicle("TEM0005", "ORANGE", 5, 6));
		vehicles.add(new Vehicle("TEM0006", "GRAY", 7, 8));
		jdbcVehicleDao.insert(vehicles);

		Vehicle findVehicle = jdbcVehicleDao.findByVehicleNo("TEM0005");
		System.out.println(findVehicle);
		System.out.println("------- findAll --------");
		jdbcVehicleDao.findAll().forEach(System.out::println);
		
		System.out.println("------- select color --------");
		System.out.println(jdbcVehicleDao.getColor("TEM0003"));
		
		System.out.println("------- Count All --------");
		System.out.println(jdbcVehicleDao.countAll());
	}
}
