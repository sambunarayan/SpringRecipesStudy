package com.apress.springrecipes.mobile.controller;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String index() {
		return "home";
	}
	
//	@RequestMapping("/home")
//	public String index(Device device) {
//		System.out.println("currentDevice -> " + request.getAttribute("currentDevice"));
//		Device device = DeviceUtils.getCurrentDevice(request);
//		System.out.println("currentDevice -> " + device);
//		if (device.isMobile()) {
//			return "mobile/home";
//		} else if (device.isTablet()) {
//			return "tablet/home";
//		} else {
//			return "home";
//		}
//	}
}
