package com.apress.springrecipes.process;

import org.springframework.batch.item.ItemProcessor;

import com.apress.springrecipes.beans.UserRegistration;

public class UserDuplicateCheckProcess implements ItemProcessor<UserRegistration, UserRegistration> {

	@Override
	public UserRegistration process(UserRegistration item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
