package com.mrj.learningportal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrj.learningportal.entity.CategoryEntity;
import com.mrj.learningportal.service.CategoryService;
import com.mrj.learningportal.service.CourseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
	
	private CategoryService categoryService;
	
	@GetMapping
	public List<CategoryEntity> showAllCategories()
	{
		return categoryService.findAllCategories();
	}
}
