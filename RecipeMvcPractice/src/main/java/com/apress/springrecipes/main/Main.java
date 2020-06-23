package com.apress.springrecipes.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.apress.springrecipes.domain.Members;

public class Main {
	public static void main(String[] args) {
		final String url = "http://localhost:8080/members.json";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		System.out.println("result --> " + result);
		
		final String url2 = "http://localhost:8080/member/test/{memberid}";
		Map<String, String> params = new HashMap<>();
		params.put("memberid", "Member1");
		Members resultMembers = restTemplate.getForObject(url2, Members.class, params);
		System.out.println("path variable --> " + resultMembers);
	}
}
