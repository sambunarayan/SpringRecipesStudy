package com.apress.springrecipes.service;

import java.util.List;

import com.apress.springrecipes.domain.Reservation;
import com.apress.springrecipes.exception.ReservationNotAvailableException;

public interface ReservationService {
	public List<Reservation> query(String courtName);
	
	public void make(Reservation reservation) throws ReservationNotAvailableException;
}
