package com.apress.springrecipes.cotroller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apress.springrecipes.domain.Reservation;
import com.apress.springrecipes.exception.ReservationWebException;
import com.apress.springrecipes.service.ReservationService;

@Controller
@RequestMapping("/reservationSummary*")
public class ReservationSummaryController {
	
	private ReservationService reservationService;
	
	@Autowired
	public ReservationSummaryController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String generateSummary(
			@RequestParam(required=true, value="date")
			String selectedDate,
			Model model) {
		System.out.println("reservationSummary ------> " + selectedDate);
		List<Reservation> reservations = Collections.emptyList();
		try {
			Date summaryDate = new SimpleDateFormat("yyyy-MM-dd").parse(selectedDate);
			reservations = reservationService.findByDate(summaryDate);
		} catch (ParseException ex) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			throw new ReservationWebException("Invalid date format for reservation summary", new Date(), sw.toString());
		}
		model.addAttribute("reservations", reservations);
		return "reservationSummary";
	}
}
