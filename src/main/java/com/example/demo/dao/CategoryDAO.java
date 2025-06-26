package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.interfaces.ICategoryDAO;
import com.example.demo.repository.CategoryRepository;

@Repository("categoryDAO")
@Profile({ "dev", "default" })
public class CategoryDAO implements ICategoryDAO {
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDAO(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public void createCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		var category = categoryRepository.findById(id).get();

		categoryRepository.deleteById(category.getId());
	}

}