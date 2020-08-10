package com.apress.springrecipes.course.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apress.springrecipes.course.Course;

@Repository("courseDao")
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
	@Transactional
	public Course store(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(course);
		return course;
	}

	@Override
	@Transactional
	public void delete(Long courseId) {
		Session session = sessionFactory.getCurrentSession();
		Course course = session.get(Course.class, courseId);
		session.delete(course);
	}

	@Override
	@Transactional(readOnly = true)
	public Course findById(Long courseId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Course.class, courseId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT c FROM Course c ", Course.class).list();
	}

}
