package com.apress.springrecipes.service;

import java.util.Collection;

import com.apress.springrecipes.beans.UserRegistration;

public interface UserUpdateService {
	Collection<UserRegistration> readUserRegistrastion();
	UserRegistration updateUser(UserRegistration userRegistration);
}
