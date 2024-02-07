package com.mrj.learningportal.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mrj.learningportal.dto.CourseResponseDto;
import com.mrj.learningportal.entity.CategoryEntity;
import com.mrj.learningportal.entity.CourseEntity;
import com.mrj.learningportal.repository.CourseRepository;
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

	@Override
	public CourseEntity findCourseByAuthor(String author) {
		return courseRepository.findByAuthor(author);
	}
	
	@Override
	public CourseResponseDto mapCourseEntitytoCourseDto(CourseEntity courseEntity)
	{
		CourseResponseDto course = new CourseResponseDto();
		course.setId(courseEntity.getId());
		course.setName(courseEntity.getName());
		course.setAuthor(courseEntity.getAuthor());
		course.setCategory(courseEntity.getCategoryEntity().getName());
		course.setDesc(courseEntity.getDesc());
		course.setEnrolledUsers(courseEntity.getEnrolledUsers().stream().map(pred -> pred.getUserEntity().getName()).collect(Collectors.toList()));
		course.setEnrolledUserCount(course.getEnrolledUsers().size());
		return course;
	}
}
