package com.apress.springrecipes.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.apress.springrecipes.domain.Player;
import com.apress.springrecipes.domain.Reservation;
import com.apress.springrecipes.service.ReservationService;

@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {
	
	private final ReservationService reservationService;
	
	@Autowired
	public ReservationFormController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@RequestParam(required=false, value="username") String username, Model model) {
		Reservation reservation = new Reservation();
		reservation.setPlayer(new Player(username, null));
		model.addAttribute("reservation", reservation);
		return "reservationForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("reservation") Reservation reservation, BindingResult result, SessionStatus status) {
		reservationService.make(reservation);
		return "redirect:reservationSuccess";
	}
}
