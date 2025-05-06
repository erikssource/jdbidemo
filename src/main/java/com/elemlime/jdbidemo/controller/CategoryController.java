package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Category;
import com.elemlime.jdbidemo.model.request.CreateCategory;
import com.elemlime.jdbidemo.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(final CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<Category> listAll() {
    return categoryService.listAll();
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable UUID id) {
    return categoryService.findById(id);
  }

  @GetMapping("/name/{name}")
  public Category findByName(@PathVariable("name") String name) {
    return categoryService.findByName(name);
  }

  @PostMapping
  public Category create(@RequestBody @Valid CreateCategory categoryData) {
    return categoryService.create(categoryData.getName());
  }
}
