package com.apress.springrecipes.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.apress.springrecipes.vehicle.Vehicle;

public class NamedParameterJdbcVehicleDao extends NamedParameterJdbcDaoSupport implements VehicleDao {

	private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES (:color, :wheel, :seat, :vehicleNo) ";
	
	@Override
	public void insert(Vehicle vehicle) {
		getNamedParameterJdbcTemplate().update(INSERT_SQL, toParameterMap(vehicle));
	}

	@Override
	public void insert(Collection<Vehicle> vehicles) {
		SqlParameterSource[] sources = vehicles.stream()
                .map(v -> new BeanPropertySqlParameterSource(v))
                .toArray(size -> new SqlParameterSource[size]);
        getNamedParameterJdbcTemplate().batchUpdate(INSERT_SQL, sources);
	}

	@Override
	public void update(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void truncate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getColor(String vehicleNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Map<String, Object> toParameterMap(Vehicle vehicle) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("vehicleNo", vehicle.getVehicleNo());
		parameters.put("color", vehicle.getColor());
		parameters.put("wheel", vehicle.getWheel());
		parameters.put("seat", vehicle.getSeat());
		return parameters;
	}

}
