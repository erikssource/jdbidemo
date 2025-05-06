package com.elemlime.jdbidemo.service.impl;

import com.elemlime.jdbidemo.dao.CategoryDao;
import com.elemlime.jdbidemo.exception.RowNotFoundException;
import com.elemlime.jdbidemo.model.Category;
import com.elemlime.jdbidemo.service.CategoryService;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
  private final CategoryDao categoryDao;

  public CategoryServiceImpl(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @Override
  public List<Category> listAll() {
    return categoryDao.getAll();
  }

  @Override
  public Category findById(UUID id) {
    return categoryDao
        .getCategoryById(id)
        .orElseThrow(
            () ->
                new RowNotFoundException("Category not found for id: " + id));
  }

  @Override
  public Category findByName(String name) {
    return categoryDao
        .getCategoryByName(name)
        .orElseThrow(() -> new RowNotFoundException("Category not found for name: " + name));
  }

  @Override
  public Category create(String categoryName) {
    return categoryDao.createCategory(categoryName);
  }
}
