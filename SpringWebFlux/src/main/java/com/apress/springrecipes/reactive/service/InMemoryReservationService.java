package com.apress.springrecipes.reactive.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.apress.springrecipes.reactive.domain.Player;
import com.apress.springrecipes.reactive.domain.Reservation;
import com.apress.springrecipes.reactive.domain.SportType;

import reactor.core.publisher.Flux;

@Service
public class InMemoryReservationService implements ReservationService {
	
	public static final SportType TENNIS = new SportType(1, "Tennis");
	public static final SportType SOCCER = new SportType(2, "Soccer");

	private final List<Reservation> reservations = new ArrayList<>();

	public InMemoryReservationService() {
		reservations.add(new Reservation("Tennis1", LocalDate.now(), 16, new Player("Roger", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis2", LocalDate.now(), 20, new Player("James", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis3", LocalDate.now(), 21, new Player("James1", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis4", LocalDate.now(), 22, new Player("James2", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis5", LocalDate.now(), 23, new Player("James3", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis6", LocalDate.now(), 23, new Player("James4", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis7", LocalDate.now(), 16, new Player("James5", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis8", LocalDate.now(), 14, new Player("James6", "N/A"), TENNIS));
		
		for (int i = 0; i < 5; i++) {
			reservations.add(new Reservation("Tennis10", LocalDate.now(), 14, new Player("Emitter" + i, "N/A"), TENNIS));
		}
	}

	@Override
	public Flux<Reservation> query(String courtName) {
		return Flux.fromIterable(reservations)
				.filter(r->Objects.equals(r.getCourtName(), courtName));
	}

}
