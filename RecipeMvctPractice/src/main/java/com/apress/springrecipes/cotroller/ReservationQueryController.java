package com.apress.springrecipes.cotroller;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apress.springrecipes.domain.Reservation;
import com.apress.springrecipes.service.ReservationService;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {

	private final ReservationService reservationService;
	
	public ReservationQueryController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping
	public void setupForm() {}
	
	@PostMapping
	public String submitForm(@RequestParam("courtName") String courtName, Model model) {
		System.out.println("submitForm --> " + courtName);
		List<Reservation> reservations = Collections.emptyList();
		if (courtName != null) {
			reservations = reservationService.query(courtName);
		}
		model.addAttribute("reservations", reservations);
		return "reservationQuery";
	}
}
