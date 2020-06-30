package com.apress.springrecipes.reactive.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/welcome")
@AllArgsConstructor
public class WelcomeController {

	@GetMapping
	public String welcome(Model model) {
		model.addAttribute("today", Mono.just(LocalDate.now()));
		return "welcome";	
	}

}
