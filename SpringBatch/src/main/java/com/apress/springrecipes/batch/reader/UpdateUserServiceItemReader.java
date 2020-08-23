package com.apress.springrecipes.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.apress.springrecipes.beans.UserRegistration;
import com.apress.springrecipes.service.UserUpdateService;

public class UpdateUserServiceItemReader implements ItemReader<UserRegistration> {
	
	private final UserUpdateService userUpdateService;
	
	public UpdateUserServiceItemReader(UserUpdateService userUpdateService) {
		this.userUpdateService = userUpdateService;
	}

	@Override
	public UserRegistration read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return userUpdateService.readUserRegistrastion().stream().findFirst().orElse(null);
	}

}
