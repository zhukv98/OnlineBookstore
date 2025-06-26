package com.example.demo.interfaces;
import java.util.List;

import com.example.demo.entity.Category;

public interface ICategoryDAO {
    List<Category> findAllCategories();

    Category findCategoryById(Long id);

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long id);
}