package com.apress.springrecipes.dao;

import com.apress.springrecipes.beans.UserRegistration;

public interface UserDao {
	void merge(UserRegistration userRegistration);
}
