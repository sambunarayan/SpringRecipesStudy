package com.apress.springrecipes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import com.apress.springrecipes.config.RootConfig;
import com.apress.springrecipes.dao.JdbcVehicleDao;
import com.apress.springrecipes.dao.NamedParameterJdbcVehicleDao;
import com.apress.springrecipes.dao.PlainJdbcVehicleDao;
import com.apress.springrecipes.dao.VehicleDao;
import com.apress.springrecipes.vehicle.Vehicle;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);

		VehicleDao vehicleDao = new PlainJdbcVehicleDao(context.getBean(DataSource.class));

		vehicleDao.truncate();

		Vehicle vehicle = new Vehicle("TEM0001", "Red", 4, 4);
		vehicleDao.insert(vehicle);
		vehicle = vehicleDao.findByVehicleNo(vehicle.getVehicleNo());
		System.out.println(vehicle);

		vehicle = new Vehicle("TEM0002", "Blue", 3, 2);
		VehicleDao jdbcVehicleDao = context.getBean(JdbcVehicleDao.class);
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
		
		NamedParameterJdbcVehicleDao namedParameterDao = new NamedParameterJdbcVehicleDao();
		namedParameterDao.setDataSource(context.getBean(DataSource.class));
		try {
			namedParameterDao.insert(new Vehicle("TEM0003", "CYAN", 8, 9));
		} catch (DataAccessException e) {
			SQLException sqle = (SQLException) e.getCause();
			System.out.println("Error Code: " + sqle.getErrorCode());
			System.out.println("SQL state: " + sqle.getSQLState());
		}
		vehicles.clear();
		vehicles.add(new Vehicle("TEM0008", "BLACK", 10, 11));
		vehicles.add(new Vehicle("TEM0009", "PINK", 12, 13));
		vehicles.add(new Vehicle("TEM0010", "ORANGE", 15, 16));
		vehicles.add(new Vehicle("TEM0011", "GRAY", 17, 18));
		namedParameterDao.insert(vehicles);
		System.out.println("------- findAll --------");
		jdbcVehicleDao.findAll().forEach(System.out::println);
	}
}
