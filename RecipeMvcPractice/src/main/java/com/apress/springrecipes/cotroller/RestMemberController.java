package com.apress.springrecipes.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apress.springrecipes.domain.Members;
import com.apress.springrecipes.service.MemberService;

@Controller
public class RestMemberController {
	private final MemberService memberService;
	
	@Autowired
	public RestMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/members")
	public String getRestMember(Model model) {
		System.out.println("RestMemberController::getRestMember executed");
		
		Members members = new Members();
		members.addMembers(memberService.findAll());
		model.addAttribute("members", members);
		return "membertemplate";
	}
}
