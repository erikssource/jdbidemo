package com.elemlime.jdbidemo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.test.dao.CategoryTestDao;
import com.elemlime.jdbidemo.test.dao.TestData;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TestcontainersConfiguration.class, TestDaoConfig.class})
@SpringBootTest
public class CategoryDaoTest {
  @Autowired
  private CategoryDao categoryDao;
  @Autowired
  private CategoryTestDao categoryTestDao;

  @BeforeEach
  void setUp() {
    categoryTestDao.deleteAll();
  }

  @Test
  void testGetAll_Empty() {
    var result = categoryDao.getAll();
    assertTrue(result.isEmpty());
  }

  @Test
  void testGetAll_WithData() {
    TestData.loadCategories(categoryDao);
    var result = categoryDao.getAll();
    assertEquals(2, result.size());
    assertEquals("Boxes", result.get(0).getName());
    assertEquals("Fruits", result.get(1).getName());
  }

  @Test
  void testGetCategoryByName_Found() {
    TestData.loadCategories(categoryDao);
    var result = categoryDao.getCategoryByName("Boxes");
    assertTrue(result.isPresent());
    assertEquals("Boxes", result.get().getName());
  }

  @Test
  void testGetCategoryByName_NotFound() {
    TestData.loadCategories(categoryDao);
    var result = categoryDao.getCategoryByName("Machines");
    assertTrue(result.isEmpty());
  }

  @Test
  void testGetCategoryById_Found() {
    TestData.loadCategories(categoryDao);
    var results = categoryDao.getAll();

    var result = categoryDao.getCategoryById(results.getFirst().getId());
    assertTrue(result.isPresent());
    assertEquals("Boxes", result.get().getName());
  }

  @Test
  void testGetCategoryById_NotFound() {
    TestData.loadCategories(categoryDao);
    var result = categoryDao.getCategoryById(UUID.randomUUID());
    assertTrue(result.isEmpty());
  }

  @Test
  void testCreateCategory() {
    categoryDao.createCategory("Boats");
    var results = categoryDao.getAll();
    assertEquals(1, results.size());
    assertEquals("Boats", results.getFirst().getName());
  }
}
