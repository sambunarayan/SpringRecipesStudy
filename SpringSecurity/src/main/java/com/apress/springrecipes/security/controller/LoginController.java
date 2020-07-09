package com.apress.springrecipes.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login*")
public class LoginController {
	
	@GetMapping
	public String get() {
		System.out.println(":::::::::::: Login controller ::::::::::::");
		return "redirect:/todos";
	}
	
	@PostMapping
	public String post() {
		System.out.println(":::::::::::: Login controller ::::::::::::");
		return "redirect:/todos";
	}
}
