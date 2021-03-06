package com.apress.springrecipes.exception;

import java.util.Date;

public class ReservationNotAvailableException extends RuntimeException {
	private static final long serialVersionUID = 2956412010577834490L;
	private String courtName;
	private Date date;
	private int hour;
	
	public ReservationNotAvailableException() {}
	
	public ReservationNotAvailableException(String courtName, Date date, int hour) {
		this.courtName = courtName;
		this.date = date;
		this.hour = hour;
	}
	
	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
}
