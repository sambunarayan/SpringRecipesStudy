package com.apress.springrecipes.service;

import java.util.Date;
import java.util.List;

import com.apress.springrecipes.domain.PeriodicReservation;
import com.apress.springrecipes.domain.Reservation;
import com.apress.springrecipes.domain.SportType;
import com.apress.springrecipes.exception.ReservationNotAvailableException;

public interface ReservationService {

	public List<Reservation> query(String courtName);
	
	public void make(Reservation reservation) throws ReservationNotAvailableException;
	
	public List<SportType> getAllSportTypes();
	
	public SportType getSportType(int sportTypeId);
	
	public void makePeriodic(PeriodicReservation periodicReservation) throws ReservationNotAvailableException;
	
	public List<Reservation> findByDate(Date date);
}
