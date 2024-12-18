package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.CategoryDao;
import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;
    
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
}
