package com.mrj.learningportal.service;

import java.util.List;

import com.mrj.learningportal.entity.CategoryEntity;

public interface CategoryService {
	public List<CategoryEntity> findAllCategories();
	public CategoryEntity findCategoryByName(String name);
	public CategoryEntity addNewCategory(CategoryEntity categoryEntity);
}
