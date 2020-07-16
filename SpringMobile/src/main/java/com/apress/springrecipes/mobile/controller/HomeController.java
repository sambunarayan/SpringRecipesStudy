package com.apress.springrecipes.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String index(HttpServletRequest request) {
		System.out.println("currentDevice -> " +request.getAttribute("currentDevice"));
		return "home";
	}
}
