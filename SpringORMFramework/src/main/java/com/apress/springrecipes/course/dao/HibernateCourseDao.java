package com.apress.springrecipes.course.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.apress.springrecipes.course.Course;

public class HibernateCourseDao implements CourseDao {
	
	private final SessionFactory sessionFactory;
	
	public HibernateCourseDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
//		Configuration configuration = new Configuration()
//				.setProperty(AvailableSettings.URL, "jdbc:oracle:thin:@localhost:1521:XE")
//				.setProperty(AvailableSettings.USER, "recipes")
//				.setProperty(AvailableSettings.PASS, "recipes")
////				.setProperty(AvailableSettings.DIALECT, "oracle.jdbc.driver.OracleDriver")
//				.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true))
//				.setProperty(AvailableSettings.HBM2DDL_AUTO, "update")
//				.addClass(Course.class);
//		sessionFactory = configuration.buildSessionFactory();
	}

	@Override
	public Course store(Course course) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(course);
			tx.commit();
			return course;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Long courseId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			Course course = session.get(Course.class, courseId);
			session.delete(course);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public Course findById(Long courseId) {
		Session session = sessionFactory.openSession();
		try {
			return session.get(Course.class, courseId);
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Course> findAll() {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("SELECT c FROM Course c ", Course.class).list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
