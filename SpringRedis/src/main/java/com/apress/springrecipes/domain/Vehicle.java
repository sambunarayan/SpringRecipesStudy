package com.apress.springrecipes.domain;

import java.io.Serializable;

public class Vehicle implements Serializable {

	private static final long serialVersionUID = 5853102311543052969L;

	private String vehicleNo;
	private String color;
	private int wheel;
	private int seat;
	
	public Vehicle() {
	}
	
	public Vehicle(String vehicleNo, String color, int wheel, int seat) {
		this.vehicleNo = vehicleNo;
		this.color = color;
		this.wheel = wheel;
		this.seat = seat;
	}
	
	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	@Override
	public String toString() {
		return "["
				+ "vehicleNo:" + vehicleNo
				+ ", color:" + color
				+ ", wheel:" + wheel
				+ ", seat:" + seat
				+ "]";
	}
}
