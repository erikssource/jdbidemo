package com.elemlime.jdbidemo.service.impl;

import com.elemlime.jdbidemo.dao.CategoryDao;
import com.elemlime.jdbidemo.model.Category;
import com.elemlime.jdbidemo.service.CategoryService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category createCategory(String categoryName) {
        try  {
          var optCategory = categoryDao.getCategoryByName(categoryName);
          if (optCategory.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "categoryName already exists");
          }
          return categoryDao.insertCategory(categoryName);
        } catch (JdbiException ex) {
            log.error("Unable to insert Category into database", ex);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating Category");
        }
    }
}
