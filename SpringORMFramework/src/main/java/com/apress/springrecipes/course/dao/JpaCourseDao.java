package com.apress.springrecipes.course.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.apress.springrecipes.course.Course;

public class JpaCourseDao implements CourseDao {

	private EntityManagerFactory entityManagerFactory;
	
	public JpaCourseDao(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
//		entityManagerFactory = Persistence.createEntityManagerFactory("course");
	}

	@Override
	public Course store(Course course) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.merge(course);
			tx.commit();
			return course;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	@Override
	public void delete(Long courseId) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			Course course = manager.find(Course.class, courseId);
			manager.remove(course);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	@Override
	public Course findById(Long courseId) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		try {
			return manager.find(Course.class, courseId);
		} catch (RuntimeException e) {
			throw e;
		} finally {
			manager.close();
		}
	}

	@Override
	public List<Course> findAll() {
		EntityManager manager = entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createQuery("SELECT c FROM Course c ", Course.class);
			return query.getResultList();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			manager.close();
		}
	}
}
