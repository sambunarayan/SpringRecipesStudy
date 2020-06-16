package com.apress.springrecipes.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apress.springrecipes.domain.Member;
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
	@ResponseBody
	public Members getRestMember(Model model) {
		System.out.println("RestMemberController::getRestMember executed");
		
		Members members = new Members();
		members.addMembers(memberService.findAll());
		return members;
//		model.addAttribute("members", members);
//		return "membertemplate";
	}
	
	@RequestMapping("/member/{memberid}")
	@ResponseBody
	public Member getMember(@PathVariable("memberid")String memberid) {
		System.out.println("RestMemberController::getRestMember executed");
		return memberService.findAll(memberid);
	}
}
