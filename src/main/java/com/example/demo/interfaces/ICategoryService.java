package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.entity.Category;

public interface ICategoryService {
	
	public List<Category> findAllCategories();

	public Category findCategoryById(Long id);

	public void createCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategory(Long id);
}
