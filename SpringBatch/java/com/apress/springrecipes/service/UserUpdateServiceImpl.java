package com.apress.springrecipes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.apress.springrecipes.beans.UserRegistration;

public class UserUpdateServiceImpl implements UserUpdateService {

	private int cnt = 0;
	
	@Override
	public Collection<UserRegistration> readUserRegistrastion() {
		System.out.println(">>>>> readUserRegistrastion");
		if (cnt < 10) {
			cnt++;
			return Arrays.asList(new UserRegistration(), new UserRegistration());
		}
		return new ArrayList<>();
	}

	@Override
	public UserRegistration updateUser(UserRegistration userRegistration) {
		System.out.println(">>>>> updateUser");
		return userRegistration;
	}

}
