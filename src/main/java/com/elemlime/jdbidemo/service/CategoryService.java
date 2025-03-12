package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.CategoryDao;
import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
        Preconditions.checkNotNull(categoryName, "categoryName is null");
        Preconditions.checkArgument(!categoryName.isEmpty(), "categoryName is empty");
        Optional<Category> optCategory;
        try  {
             optCategory = categoryDao.insertCategory(categoryName);
             if (optCategory.isPresent()) {
                 return optCategory.get();
             }
             optCategory = categoryDao.getCategoryByName(categoryName);
        } catch (JdbiException ex) {
            log.error("Unable to insert Category into database", ex);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating Category");
        }
        return optCategory.orElseThrow(() -> {
                log.error("Could not insert or select category with name {}", categoryName);
                return new HttpServerErrorException(HttpStatus.NOT_FOUND, "Error creating Category");
        });
    }
}
