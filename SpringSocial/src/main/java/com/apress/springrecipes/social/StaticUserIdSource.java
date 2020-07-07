package com.apress.springrecipes.social;

import org.springframework.social.UserIdSource;

public class StaticUserIdSource implements UserIdSource {

	private static final String DEFAULT_USERID = "anonymous";
	private String userid = DEFAULT_USERID;
	
	@Override
	public String getUserId() {
		return this.userid;
	}
	
	public void setUserId(String userid) {
		this.userid = userid;
	}

}
