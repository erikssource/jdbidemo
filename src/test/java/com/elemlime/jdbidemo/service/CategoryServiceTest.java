package com.elemlime.jdbidemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.dao.CategoryDao;
import com.elemlime.jdbidemo.test.dao.CategoryTestDao;
import com.elemlime.jdbidemo.test.dao.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TestcontainersConfiguration.class, TestDaoConfig.class})
@SpringBootTest
public class CategoryServiceTest {

  @Autowired
  private CategoryDao categoryDao;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private CategoryTestDao categoryTestDao;

  @BeforeEach
  void setUp() {
    categoryTestDao.clearAll();
  }

  @Test
  void testListCategories() {
    TestData.loadCategories(categoryDao);
    var results = categoryService.getAll();

    assertEquals(2, results.size());
    assertEquals("Boxes", results.get(0).getName());
    assertEquals("Fruits", results.get(1).getName());
  }

  @Test
  void testGetCategoryById() {
    TestData.loadCategories(categoryDao);
    var results = categoryService.getAll();

    var result = categoryService.getById(results.getFirst().getId());
    assertEquals("Boxes", result.getName());

    result = categoryService.getById(results.getLast().getId());
    assertEquals("Fruits", result.getName());
  }
}
