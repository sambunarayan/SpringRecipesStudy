package com.apress.springrecipes.converter;

import org.springframework.core.convert.converter.Converter;

import com.apress.springrecipes.domain.SportType;
import com.apress.springrecipes.service.ReservationService;

public class SportTypeConverter implements Converter<String, SportType> {

	private ReservationService reservationService;
	
	public SportTypeConverter(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@Override
	public SportType convert(String source) {
		System.out.println("SportTypeConverter :::::: convert is executed.");
		int sportTypeId = Integer.parseInt(source);
		SportType sportType = reservationService.getSportType(sportTypeId);
		return sportType;
	}
}
