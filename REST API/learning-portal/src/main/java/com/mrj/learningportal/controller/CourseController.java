package com.mrj.learningportal.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrj.learningportal.dto.CourseRequestDto;
import com.mrj.learningportal.dto.CourseResponseDto;
import com.mrj.learningportal.entity.CategoryEntity;
import com.mrj.learningportal.entity.CourseEntity;
import com.mrj.learningportal.service.CategoryService;
import com.mrj.learningportal.service.CourseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {
	
	private CourseService courseService;
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<Object> showAllCourses()
	{
		List<CourseEntity> courses = courseService.findAllCourse();
		
		if(courses != null && !courses.isEmpty())
		{
			List<CourseResponseDto> courseresp = courses.stream().map(courseService::mapCourseEntitytoCourseDto).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(courseresp);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No courses found!");
	}
	
	@PostMapping
	public ResponseEntity<Object> addCourse(@RequestBody CourseRequestDto courseRequestDto)
	{
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setName(courseRequestDto.getName());
		courseEntity.setAuthor(courseRequestDto.getAuthor());
		courseEntity.setDesc(courseRequestDto.getDesc());
		if(categoryService.findCategoryByName(courseRequestDto.getCategory()) != null)
		{
			courseEntity.setCategoryEntity(categoryService.findCategoryByName(courseRequestDto.getCategory()));
		}
		else
		{
			CategoryEntity categoryEntity = new CategoryEntity();
			categoryEntity.setName(courseRequestDto.getCategory());
			categoryService.addNewCategory(categoryEntity);
			courseEntity.setCategoryEntity(categoryService.findCategoryByName(courseRequestDto.getCategory()));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseEntity));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCourse(@PathVariable(value = "id") Long id, @RequestBody CourseRequestDto courseRequestDto)
	{
		CourseEntity courseEntity = courseService.findCourseByAuthor(courseRequestDto.getAuthor());
		courseEntity.setName(courseRequestDto.getName());
		courseEntity.setDesc(courseRequestDto.getDesc());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(courseService.addCourse(courseEntity));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> showCourseById(@PathVariable(value = "id") Long id)
	{
		Optional<CourseEntity> courseEntity = courseService.findCourseById(id);
		if(courseEntity.isPresent())
		{
			CourseEntity course = courseEntity.get();
			CourseResponseDto courseresp = new CourseResponseDto();
			courseresp.setId(course.getId());
			courseresp.setAuthor(course.getAuthor());
			courseresp.setName(course.getName());
			courseresp.setDesc(course.getDesc());
			courseresp.setCategory(course.getCategoryEntity().getName());
			courseresp.setEnrolledUsers(course.getEnrolledUsers().stream().map(func -> func.getCourseEntity().getName()).collect(Collectors.toList()));
			courseresp.setEnrolledUserCount(courseresp.getEnrolledUsers().size());
			return ResponseEntity.status(HttpStatus.FOUND).body(courseresp);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found!");
		}	
	}
	
}
