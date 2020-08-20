package com.apress.springrecipes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import com.apress.springrecipes.beans.UserRegistration;
import com.apress.springrecipes.dao.UserRegistrationDao;

public class UserUpdateServiceImpl implements UserUpdateService {

	private int cnt = 0;
	private BufferedReader reader;
	
	@Autowired
	private UserRegistrationDao dao;
	
	public UserUpdateServiceImpl(Resource input) {
		try {
			reader = new BufferedReader(new InputStreamReader(input.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Collection<UserRegistration> readUserRegistrastion() {
		System.out.println(">>>>> readUserRegistrastion");
		Optional<UserRegistration> op = getUserRegistration();
		if (op.isPresent()) {
			return Arrays.asList(op.get());
		}
		return new ArrayList<>();
	}

	@Override
	public UserRegistration updateUser(UserRegistration userRegistration) {
		System.out.println(">>>>> updateUser");
		dao.merge(userRegistration);
		return userRegistration;
	}

	private Optional<UserRegistration> getUserRegistration() {
		UserRegistration userRegistration = new UserRegistration();
		
		try {
			String line = reader.readLine();
			if (line != null) {
				String[] items = line.split(",");
				int idx = 0;
				userRegistration.setId(Long.parseLong(items[idx++]));
				userRegistration.setFirstName(items[idx++]);
				userRegistration.setLastName(items[idx++]);
				userRegistration.setCompany(items[idx++]);
				userRegistration.setAddress(items[idx++]);
				userRegistration.setCity(items[idx++]);
				userRegistration.setState(items[idx++]);
				userRegistration.setZip(items[idx++]);
				userRegistration.setCounty(items[idx++]);
				userRegistration.setUrl(items[idx++]);
				userRegistration.setPhoneNumber(items[idx++]);
				userRegistration.setFax(items[idx++]);
				return Optional.of(userRegistration);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
