package com.apress.springrecipes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apress.springrecipes.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final List<Member> members;
	
	public MemberServiceImpl() {
		this.members = new ArrayList<>();
		this.members.add(new Member("Member1", "070", "email1"));
		this.members.add(new Member("Member2", "080", "email2"));
	}

	@Override
	public List<Member> findAll() {
		return this.members;
	}

	@Override
	public Member findAll(String memberid) {
		return this.members.stream()
				.filter(member->member.getName().equals(memberid))
				.findFirst()
				.get();
	}
	
}
