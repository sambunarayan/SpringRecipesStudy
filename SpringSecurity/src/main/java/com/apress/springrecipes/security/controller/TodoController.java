package com.apress.springrecipes.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos")
public class TodoController {
	
	@PostMapping
	public String post() {
		System.out.println(":::::::::::: todos controller ::::::::::::");
		return "todos";
	}
}
