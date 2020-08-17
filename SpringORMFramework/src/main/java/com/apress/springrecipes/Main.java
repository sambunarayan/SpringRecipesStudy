package com.apress.springrecipes;

import java.util.GregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.config.CourseConfiguration;
import com.apress.springrecipes.course.Course;
import com.apress.springrecipes.course.Reservation;
import com.apress.springrecipes.course.dao.CourseDao;
import com.apress.springrecipes.course.dao.HibernateReservationDao;
import com.apress.springrecipes.course.dao.JpaCourseDao;
import com.apress.springrecipes.course.dao.JpaDao;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(CourseConfiguration.class);
		
		CourseDao courseDao = context.getBean(CourseDao.class);
		
		Course course = new Course();
//		course.setId(5L);
		course.setTitle("Core Spring");
		course.setBeginDate(new GregorianCalendar(2007, 8, 1).getTime());
		course.setEndDate(new GregorianCalendar(2007, 9, 1).getTime());
		course.setFee(100);
		
		System.out.println("\nCourse after persisting");
		courseDao.store(course);
		System.out.println(">>>>>>> after store");
		System.out.println(course);
		System.out.println(">>>>>>> find by id");
		System.out.println(courseDao.findById(course.getId()));
		
		HibernateReservationDao reservDao = new HibernateReservationDao();
		Reservation reserv = new Reservation();
		reserv.setCustomer("Customer 1");
		reserv.setFromDate(new GregorianCalendar(2020, 8, 1).getTime());
		reserv.setToDate(new GregorianCalendar(2020, 10, 1).getTime());
		reserv.setAmount(50000);
		reservDao.book(reserv);
		
		System.out.println(">>>>>>> after book");
		System.out.println(reserv);
		System.out.println(">>>>>>> find by id");
		System.out.println(reservDao.findById(reserv.getId()));
		
		JpaDao jpaCourseDao = context.getBean(JpaCourseDao.class);
//		course.setId(5L);
		course.setTitle("JPA Test");
		course.setBeginDate(new GregorianCalendar(2007, 8, 1).getTime());
		course.setEndDate(new GregorianCalendar(2007, 9, 1).getTime());
		course.setFee(113);
		jpaCourseDao.store(course);
		System.out.println(">>>>>>>>>>>> Test JPA  ");
		System.out.println("ourse -> " + jpaCourseDao.findById(course.getId()));
		
	}
}
