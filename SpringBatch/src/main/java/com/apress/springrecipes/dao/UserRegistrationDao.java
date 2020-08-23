package com.apress.springrecipes.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.apress.springrecipes.beans.UserRegistration;

@Repository("userDao")
public class UserRegistrationDao implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void merge(UserRegistration userRegistration) {
		entityManager.merge(userRegistration);
	}

}
