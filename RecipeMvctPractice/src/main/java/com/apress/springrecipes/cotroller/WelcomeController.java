package com.apress.springrecipes.cotroller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apress.springrecipes.exception.ReservationNotAvailableException;

@Controller
@RequestMapping("/welcome/*")
public class WelcomeController {

	@RequestMapping(value="get", method = RequestMethod.GET)
	public String welcomeGet(@RequestHeader MultiValueMap<String, String> headers, Model model) {
		System.out.println(">>>>>>>>> welcome Controller GET <<<<<<<<<");
		System.out.println(headers);
		Date today = new Date();
		model.addAttribute("today", today);
		return "welcome";
	}

	@RequestMapping(value="post", method = RequestMethod.POST)
	public String welcomePost(@RequestHeader MultiValueMap<String, String> headers, Model model) {
		System.out.println(">>>>>>>>> welcome Controller POST <<<<<<<<<");
		System.out.println(headers);
		Date today = new Date();
		model.addAttribute("today", today);
		return "welcome";
	}

	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public String welcomeExc(@RequestHeader MultiValueMap<String, String> headers, Model model)
			throws ReservationNotAvailableException {
		ReservationNotAvailableException exception = new ReservationNotAvailableException();
		exception.setCourtName("Test");
		exception.setDate(new Date());
		exception.setHour(1);
		throw exception;
	}
}
