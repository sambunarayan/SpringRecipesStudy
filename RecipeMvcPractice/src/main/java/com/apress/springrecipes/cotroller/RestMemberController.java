package com.apress.springrecipes.cotroller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	
//	@RequestMapping(value="/members", produces=MediaType.APPLICATION_XML_VALUE)
//	public String getRestMembersJson(Model model) {
//		Members members = new Members();
//		members.addMembers(memberService.findAll());
//		model.addAttribute("members", members);
//		return "xmlmembertemplate";
//	}
//	
//	@RequestMapping(value="/members", produces=MediaType.APPLICATION_JSON_VALUE)
//	public String getRestMembersXml(Model model) {
//		Members members = new Members();
//		members.addMembers(memberService.findAll());
//		model.addAttribute("members", members);
//		return "jsonmembertemplate";
//	}
//	
	@RequestMapping("/member/*/{memberid}")
	@ResponseBody
	public ResponseEntity<Members> getMember(@PathVariable("memberid")String memberid) {
		System.out.println("RestMemberController::getRestMember executed");
		Member member = memberService.findAll(memberid);
		if (member != null) {
			Members members = new Members();
			members.addMember(member);
			return new ResponseEntity<>(members, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping("/reservations/{date}")
	@ResponseBody
	public String getReservation(@PathVariable("date") Date resDate) {
		return resDate.toString();
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
