package com.apress.springrecipes.reactive.service;

import com.apress.springrecipes.reactive.domain.Reservation;

import reactor.core.publisher.Flux;

public interface ReservationService {
	Flux<Reservation> query(String courtName);
}
