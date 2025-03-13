package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.CategoryDao;
import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Slf4j
@Service
public class CategoryService {
    private final CategoryDao categoryDao;
    
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

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
