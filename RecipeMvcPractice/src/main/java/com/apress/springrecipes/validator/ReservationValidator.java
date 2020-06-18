package com.apress.springrecipes.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.apress.springrecipes.domain.Reservation;

@Component
public class ReservationValidator implements Validator {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		return Reservation.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courtName", "required.courtName", "Court name is required.");
		ValidationUtils.rejectIfEmpty(errors, "date", "required.date", "Date is required");
		ValidationUtils.rejectIfEmpty(errors, "hour", "required.hour", "Hour is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player.name", "required.playerName", "Player name is required.");
		ValidationUtils.rejectIfEmpty(errors, "sportType", "required.sportTpye", "Sport Type is required.");
		
		Reservation reservation = (Reservation) target;
		Date date = reservation.getDate();
		int hour = reservation.getHour();
		
		if (date != null ) {
			
		} else {
			
		}
	}

}
