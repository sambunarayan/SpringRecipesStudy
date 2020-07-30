package com.apress.springrecipes;

import java.util.GregorianCalendar;

import com.apress.springrecipes.course.Course;
import com.apress.springrecipes.course.dao.CourseDao;
import com.apress.springrecipes.course.dao.HibernateCourseDao;

public class Main {

	public static void main(String[] args) {
		CourseDao courseDao = new HibernateCourseDao();
		
		Course course = new Course();
		course.setTitle("Core Spring");
		course.setBeginDate(new GregorianCalendar(2007, 8, 1).getTime());
		course.setEndDate(new GregorianCalendar(2007, 9, 1).getTime());
		course.setFee(1000);
		
		System.out.println("\nCourse after persisting");
		System.out.println(course);
		
		courseDao.store(course);
	}
}
