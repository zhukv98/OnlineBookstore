package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.interfaces.ICategoryDAO;
import com.example.demo.interfaces.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryDAO categoryDAO;


    public CategoryService(ICategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Category> findAllCategories() {
		return categoryDAO.findAllCategories();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Category findCategoryById(Long id) {
		return categoryDAO.findCategoryById(id);
	}

	@Override
	public void createCategory(Category category) {
		categoryDAO.createCategory(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDAO.updateCategory(category);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryDAO.deleteCategory(id);
	}
}
