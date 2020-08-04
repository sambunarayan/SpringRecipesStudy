package com.apress.springrecipes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import com.apress.springrecipes.vehicle.Vehicle;

public class PlainJdbcVehicleDao implements VehicleDao {

	private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES (?, ?, ?, ?) ";
	private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR =?, WHEEL=?, SEAT=? WHERE VEHICLE_NO = ? ";
	private static final String SELECT_ALL_SQL = "SELECT * FROM VEHICLE";
	private static final String SELECT_ONE_SQL = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ? ";
	private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO = ? ";
	private static final String TRUNCATE_SQL = "TRUNCATE TABLE VEHICLE";

	private DataSource dataSource;

	public PlainJdbcVehicleDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Vehicle vehicle) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			prepareStatement(ps, vehicle);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(Collection<Vehicle> vehicles) {
		vehicles.forEach(this::insert);
	}

	@Override
	public void update(Vehicle vehicle) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
			int idx = 0;
			ps.setString(++idx, vehicle.getColor());
			ps.setInt(++idx, vehicle.getWheel());
			ps.setInt(++idx, vehicle.getSeat());
			ps.setString(++idx, vehicle.getVehicleNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Vehicle vehicle) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
			ps.setString(1, vehicle.getVehicleNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_ONE_SQL)) {
			ps.setString(1, vehicleNo);

			Vehicle vehicle = null;
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					int idx = 0;
					vehicle = toVehicle(rs);
				}
			}
			return vehicle;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Vehicle> findAll() {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL)) {

			List<Vehicle> vehicles = new ArrayList<>();
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int idx = 0;
					vehicles.add(toVehicle(rs));
				}
			}
			return vehicles;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void truncate() {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(TRUNCATE_SQL)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Vehicle toVehicle(ResultSet rs) throws SQLException {
		int idx = 0;
		return new Vehicle(rs.getString(++idx), rs.getString(++idx), rs.getInt(++idx), rs.getInt(++idx));
	}

	private void prepareStatement(PreparedStatement ps, Vehicle vehicle) throws SQLException {
		int idx = 0;
		ps.setString(++idx, vehicle.getColor());
		ps.setInt(++idx, vehicle.getWheel());
		ps.setInt(++idx, vehicle.getSeat());
		ps.setString(++idx, vehicle.getVehicleNo());
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

}
