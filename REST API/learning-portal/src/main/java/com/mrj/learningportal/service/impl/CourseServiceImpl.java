package com.mrj.learningportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mrj.learningportal.entity.CategoryEntity;
import com.mrj.learningportal.entity.CourseEntity;
import com.mrj.learningportal.repository.CourseRepository;
import com.mrj.learningportal.service.CategoryService;
import com.mrj.learningportal.service.CourseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

	private CourseRepository courseRepository;
	
	@Override
	public List<CourseEntity> findAllCourse() {
		return courseRepository.findAll();
	}

	@Override
	public Optional<CourseEntity> findCourseById(Long id) {
		return courseRepository.findById(id);
	}

	@Override
	public CourseEntity addCourse(CourseEntity courseEntity) {
		return courseRepository.save(courseEntity);
	}

	@Override
	public List<CourseEntity> findCourseByCategory(CategoryEntity categoryEntity) {
		return courseRepository.findByCategoryEntity(categoryEntity);
	}
	
}
