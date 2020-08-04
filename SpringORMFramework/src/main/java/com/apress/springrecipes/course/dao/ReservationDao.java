package com.apress.springrecipes.course.dao;

import java.util.List;

import com.apress.springrecipes.course.Reservation;

public interface ReservationDao {
	Reservation book(Reservation reserv);
	void delete(Long reservId);
	Reservation findById(Long reservId);
	List<Reservation> findAll();
}
