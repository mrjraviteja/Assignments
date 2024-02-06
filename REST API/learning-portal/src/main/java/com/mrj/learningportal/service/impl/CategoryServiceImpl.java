package com.mrj.learningportal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrj.learningportal.entity.CategoryEntity;
import com.mrj.learningportal.repository.CategoryRepository;
import com.mrj.learningportal.service.CategoryService;
import com.mrj.learningportal.service.CourseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryEntity> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public CategoryEntity findCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public CategoryEntity addNewCategory(CategoryEntity categoryEntity) {
		return categoryRepository.save(categoryEntity);
	}
	
}
