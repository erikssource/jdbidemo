package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Category;
import com.elemlime.jdbidemo.model.CategoryData;
import com.elemlime.jdbidemo.service.CategoryService;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    
    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    public Category create(@RequestBody CategoryData categoryData) {
        return categoryService.createCategory(categoryData.getName());
    }
}
