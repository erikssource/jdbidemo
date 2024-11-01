package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Category;
import com.elemlime.jdbidemo.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    
    @Autowired
    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }
}
