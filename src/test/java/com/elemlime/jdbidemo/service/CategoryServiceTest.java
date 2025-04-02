package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.test.dao.CategoryTestDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TestcontainersConfiguration.class, TestDaoConfig.class})
@SpringBootTest
public class CategoryServiceTest {

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
    var results = categoryService.getAll();
    System.out.println(results);
  }
}
