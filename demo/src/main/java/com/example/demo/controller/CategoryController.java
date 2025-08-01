package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
@Controller
public class CategoryController {
	@Autowired
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;

	}

	@RequestMapping("/categories")
	public String findAllCategories(Model model) {

		model.addAttribute("categories", categoryService.findAllCategories());
		return "list-categories";
	}

	@RequestMapping("/category/{id}")
	public String findCategoryById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("category", categoryService.findCategoryById(id));
		return "list-category";
	}

	@GetMapping("/addCategory")
	public String showCreateForm(Category category) {
		return "add-category";
	}

	@RequestMapping("/add-category")
	public String createCategory(Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-category";
		}

		categoryService.createCategory(category);
		model.addAttribute("category", categoryService.findAllCategories());
		return "redirect:/categories";
	}

	@GetMapping("/updateCategory/{id}")
	public ResponseEntity<String> showUpdateForm(@PathVariable("id") Long id, Model model) {
		Category category = categoryService.findCategoryById(id);
		if (category == null) {
			return ResponseEntity.notFound().build();
		}
		model.addAttribute("category", category);
		return ResponseEntity.ok("update-category");
		// return "update-category"; // Uncomment if you want to return the update form
		// directly
	}

	@RequestMapping("/update-category/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable("id") Long id, Category category, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Error in updating category");
		}

		categoryService.updateCategory(category);
		model.addAttribute("category", categoryService.findAllCategories());
		return ResponseEntity.ok("redirect:/categories");
		// return "redirect:/categories"; // Uncomment if you want to redirect after
		// update
		// return "list-categories"; // Uncomment if you want to return the list view
		// directly
		// return "update-category"; // Uncomment if you want to return the update form
	}

	@RequestMapping("/remove-category/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id, Model model) {
		categoryService.deleteCategory(id);
		model.addAttribute("category", categoryService.findAllCategories());
		return ResponseEntity.ok("redirect:/categories");
		// return "redirect:/categories"; // Uncomment if you want to redirect after
		// deletion
		// return "list-categories"; // Uncomment if you want to return the list view
		// directly
	}

}
