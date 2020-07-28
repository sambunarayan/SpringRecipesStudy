package com.apress.springrecipes.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.apress.springrecipes.vehicle.Vehicle;

public class JdbcVehicleDao extends JdbcDaoSupport implements VehicleDao {
	
	private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES (?, ?, ?, ?) ";
	private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR =?, WHEEL=?, SEAT=? WHERE VEHICLE_NO = ? ";
	private static final String SELECT_ALL_SQL = "SELECT * FROM VEHICLE";
	private static final String SELECT_ONE_SQL = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ? ";
	private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO = ? ";
	private static final String SELECT_COLOR_SQL = "SELECT COLOR FROM VEHICLE WHERE VEHICLE_NO = ?";
	private static final String COUNT_ALL_SQL = "SELECT COUNT(*) FROM VEHICLE";
	
	
	public JdbcVehicleDao() {
	}

	@Override
	public void insert(Vehicle vehicle) {
		getJdbcTemplate().update(con->{
			PreparedStatement ps = con.prepareStatement(INSERT_SQL);
			prepareStatement(ps, vehicle);
			return ps;
		});
	}

	@Override
	public void insert(Collection<Vehicle> vehicles) {
		getJdbcTemplate().batchUpdate(INSERT_SQL, vehicles, vehicles.size(), this::prepareStatement);
	}

	@Override
	public void update(Vehicle vehicle) {
		getJdbcTemplate().update(UPDATE_SQL, vehicle.getColor(), vehicle.getWheel(), vehicle.getSeat(), vehicle.getVehicleNo());
	}

	@Override
	public void delete(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		final Vehicle vehicle = new Vehicle();
		getJdbcTemplate().query(SELECT_ONE_SQL, rs->{
			vehicle.setVehicleNo(rs.getString(1));
			vehicle.setColor(rs.getString(2));
			vehicle.setWheel(rs.getInt(3));
			vehicle.setSeat(rs.getInt(4));
		}, vehicleNo);
		return vehicle;
	}

	@Override
	public List<Vehicle> findAll() {
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SELECT_ALL_SQL);
		return rows.stream().map(row->{
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleNo((String)row.get("VEHICLE_NO"));
			vehicle.setColor((String)row.get("COLOR"));
			vehicle.setWheel(((BigDecimal)row.get("WHEEL")).intValue());
			vehicle.setSeat(((BigDecimal)row.get("SEAT")).intValue());
			return vehicle;
		}).collect(Collectors.toList());
	}
	
	private void prepareStatement(PreparedStatement ps, Vehicle vehicle) throws SQLException {
		int idx = 0;
		ps.setString(++idx, vehicle.getColor());
		ps.setInt(++idx, vehicle.getWheel());
		ps.setInt(++idx, vehicle.getSeat());
		ps.setString(++idx, vehicle.getVehicleNo());
	}

	@Override
	public void truncate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getColor(String vehicleNo) {
		return getJdbcTemplate().queryForObject(SELECT_COLOR_SQL, String.class, vehicleNo);
	}

	@Override
	public int countAll() {
		return getJdbcTemplate().queryForObject(COUNT_ALL_SQL, Integer.class);
	}

}
