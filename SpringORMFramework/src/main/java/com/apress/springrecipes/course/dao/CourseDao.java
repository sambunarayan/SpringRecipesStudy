package com.apress.springrecipes.course.dao;

import java.util.List;

import com.apress.springrecipes.course.Course;

public interface CourseDao {
	Course store(Course course);
	void delete(Long courseId);
	Course findById(Long courseId);
	List<Course> findAll();
}
