package org.com.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {
	
	
	@RequestMapping(value="/chatting.do", method=RequestMethod.GET)
	public ModelAndView chat(ModelAndView mv) {
		mv.setViewName("chat");
		
		log.info("controller !!!");
		System.out.println("chatting");
		
		mv.addObject("userid","userid");
		return mv;
	}
}
