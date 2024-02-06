package com.mrj.learningportal.service;

import java.util.List;
import java.util.Optional;

import com.mrj.learningportal.entity.CategoryEntity;
import com.mrj.learningportal.entity.CourseEntity;

public interface CourseService {
	public List<CourseEntity> findAllCourse();
	public Optional<CourseEntity> findCourseById(Long id);
	public CourseEntity addCourse(CourseEntity courseEntity);
	public List<CourseEntity> findCourseByCategory(CategoryEntity categoryEntity);
}
