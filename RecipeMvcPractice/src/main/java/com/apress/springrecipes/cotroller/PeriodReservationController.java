package com.apress.springrecipes.cotroller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.apress.springrecipes.domain.PeriodicReservation;
import com.apress.springrecipes.domain.Player;
import com.apress.springrecipes.service.ReservationService;
import com.apress.springrecipes.validator.PeriodicReservationValidator;

@Controller
@RequestMapping("/periodicReservationForm")
@SessionAttributes("reservation")
public class PeriodReservationController {
	private final Map<Integer, String> pageForms = new HashMap<>(3);
	private final ReservationService reservationService;
	private final PeriodicReservationValidator validator;
	
	public PeriodReservationController(ReservationService reservationService,
			PeriodicReservationValidator validator) {
		this.reservationService = reservationService;
		this.validator = validator;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.validator);
	}
	
	@PostConstruct
	public void initialize() {
		pageForms.put(0, "reservationCourtForm");
		pageForms.put(1, "reservationTimeForm");
		pageForms.put(2, "reservationPlayerForm");
	}
	
	@GetMapping
	public String setupForm(Model model) {
		PeriodicReservation reservation = new PeriodicReservation();
		reservation.setPlayer(new Player());
		model.addAttribute("reservation", reservation);
		return "reservationCourtForm";
	}
	
	@PostMapping(params = {"_cancel"})
	public String cancelForm(@RequestParam("_page")int currentPage) {
		return pageForms.get(currentPage);
	}
	
	@PostMapping(params = {"_finish"})
	public String completeForm(
			@ModelAttribute("reservation") @Validated PeriodicReservation reservation, 
			BindingResult result, 
			SessionStatus status,
			@RequestParam("_page") int currentPage) {
		
		if (!result.hasErrors()) {
			reservationService.makePeriodic(reservation);
			status.setComplete();
			return "redirect:reservationSuccess";
		} else {
			return pageForms.get(currentPage);
		}
	}
	
	@PostMapping
	public String submitForm(
			HttpServletRequest request,
			@ModelAttribute("reservation") PeriodicReservation reservation, 
			BindingResult result, 
			@RequestParam("_page") int currentPage) {
		
		int targetPage = getTargetPage(request, "_target", currentPage);
		if (targetPage < currentPage) {
			return pageForms.get(targetPage);
		}
		validatorCurrentPage(reservation, result, currentPage);
		if (!result.hasErrors()) {
			return pageForms.get(targetPage);
		} else {
			return pageForms.get(currentPage);
		}
	}
	
	@ModelAttribute("periods")
	public Map<Integer, String> periods() {
		Map<Integer, String> periods = new HashMap<>();
		periods.put(1, "Daily");
		periods.put(7, "Weekly");
		return periods;
	}
	
	private int getTargetPage(HttpServletRequest request, String paramPrefix, int currentPage) {
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if (paramName.startsWith(paramPrefix)) {
				for (int i = 0; i < WebUtils.SUBMIT_IMAGE_SUFFIXES.length; i++) {
					String suffix = WebUtils.SUBMIT_IMAGE_SUFFIXES[i];
					if (paramName.endsWith(suffix)) {
						paramName = paramName.substring(0, paramName.length() - suffix.length());
					}
				}
				return Integer.parseInt(paramName.substring(paramPrefix.length()));
			}
		}
		return currentPage;
	}
	
	private void validatorCurrentPage(PeriodicReservation reservation, BindingResult result, int currentPage) {
		switch (currentPage) {
		case 0:
			validator.validateCourt(reservation, result);
			break;
		case 1:
			validator.validateTime(reservation, result);
			break;
		case 2:
			validator.validatePlayer(reservation, result);
			break;
		}
	}
}
