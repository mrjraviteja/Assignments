package com.mrj.learningportal.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrj.learningportal.dto.CourseResponseDto;
import com.mrj.learningportal.entity.CategoryEntity;
import com.mrj.learningportal.entity.CourseEntity;
import com.mrj.learningportal.service.CategoryService;
import com.mrj.learningportal.service.CourseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
	
	private CategoryService categoryService;
	private CourseService courseService;
	
	@GetMapping
	public ResponseEntity<Object> showAllCategories()
	{
		List<CategoryEntity> categories =  categoryService.findAllCategories();
		if(categories.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(categories);

	}
	
	@GetMapping("/{category}")
	public ResponseEntity<Object> showCoursesByCategory(@PathVariable(value = "category") Long id)
	{
		Optional<CategoryEntity> category = categoryService.findCategoryById(id);
		if(category.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!");
		}
		CategoryEntity categoryEntity = category.get();
		List<CourseEntity> courses = courseService.findCourseByCategory(categoryEntity);
		List<CourseResponseDto> courseresp = courses.stream().map(courseService::mapCourseEntitytoCourseDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(courseresp);
	}
}
