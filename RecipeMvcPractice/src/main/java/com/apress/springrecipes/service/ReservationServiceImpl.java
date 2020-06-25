package com.apress.springrecipes.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.apress.springrecipes.domain.PeriodicReservation;
import com.apress.springrecipes.domain.Player;
import com.apress.springrecipes.domain.Reservation;
import com.apress.springrecipes.domain.SportType;
import com.apress.springrecipes.exception.ReservationNotAvailableException;

@Service
public class ReservationServiceImpl implements ReservationService {

	public static final SportType TENNIS = new SportType(1, "Tennis");
	public static final SportType SOCCER = new SportType(2, "Soccer");

	private final List<Reservation> reservations = new ArrayList<>();

	public ReservationServiceImpl() {
		reservations.add(new Reservation("Tennis1", new Date(), 16, new Player("Roger", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis2", new Date(), 20, new Player("James", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis3", new Date(), 21, new Player("James1", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis4", new Date(), 22, new Player("James2", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis5", new Date(), 23, new Player("James3", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis6", new Date(), 23, new Player("James4", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis7", new Date(), 16, new Player("James5", "N/A"), TENNIS));
		reservations.add(new Reservation("Tennis8", new Date(), 14, new Player("James6", "N/A"), TENNIS));
		
		for (int i = 0; i < 5; i++) {
			reservations.add(new Reservation("Tennis10", new Date(), 14, new Player("Emitter" + i, "N/A"), TENNIS));
		}
	}

	@Override
	public List<Reservation> query(String courtName) {
		return this.reservations.stream().filter(reservation -> Objects.equals(reservation.getCourtName(), courtName))
				.collect(Collectors.toList());
	}

	@Override
	public void make(Reservation reservation) throws ReservationNotAvailableException {
		long cnt = reservations.stream()
				.filter(made -> Objects.equals(made.getCourtName(), reservation.getCourtName()))
				.filter(made -> Objects.equals(made.getDate(), reservation.getDate()))
				.filter(made -> made.getHour() == reservation.getHour())
				.count();

		if (cnt > 0) {
			throw new ReservationNotAvailableException(reservation.getCourtName(), reservation.getDate(),
					reservation.getHour());
		} else {
			reservations.add(reservation);
		}
	}

	@Override
	public List<SportType> getAllSportTypes() {
		return Arrays.asList(TENNIS, SOCCER);
	}

	@Override
	public SportType getSportType(int sportTypeId) {
		switch (sportTypeId) {
		case 1:
			return TENNIS;
		case 2:
			return SOCCER;
			default :
				return null;
		}
	}

	@Override
	public void makePeriodic(PeriodicReservation periodicReservation) throws ReservationNotAvailableException {
		
		Date fromDate = periodicReservation.getFromDate();
		LocalDate localDate = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		while (localDate.isBefore(toLocalDate(periodicReservation.getToDate()))) {
			Reservation reservation = new Reservation();
			reservation.setCourtName(periodicReservation.getCourtName());
			reservation.setDate(toDate(localDate));
			reservation.setHour(periodicReservation.getHour());
			reservation.setPlayer(periodicReservation.getPlayer());
			make(reservation);
			
			localDate = localDate.plusDays(periodicReservation.getPeriod());
		}
	}

	private LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	private Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public List<Reservation> findByDate(Date date) {
		return reservations.stream()
				.filter(r->Objects.equals(r.getDate(), date))
				.collect(Collectors.toList());
	}
}
