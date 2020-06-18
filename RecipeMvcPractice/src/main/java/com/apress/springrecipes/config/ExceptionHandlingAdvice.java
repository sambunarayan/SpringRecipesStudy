package com.apress.springrecipes.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.apress.springrecipes.exception.ReservationNotAvailableException;

@ControllerAdvice
public class ExceptionHandlingAdvice {

	@ExceptionHandler(ReservationNotAvailableException.class)
	public String handle(ReservationNotAvailableException ex, Model model) {
		System.out.println(ex);
		System.out.println(ex.getCourtName());
		System.out.println(ex.getHour());
		System.out.println(ex.getDate());
		model.addAttribute("exception", ex);
		
		return "reservationNotAvailable";
	}
	
	@ExceptionHandler
	public String handleDefault(Exception ex) {
		return "error";
	}
}
