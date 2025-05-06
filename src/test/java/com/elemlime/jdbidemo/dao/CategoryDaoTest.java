package com.elemlime.jdbidemo.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
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
  private TestData testData;

  @BeforeEach
  void setUp() {
    testData.deleteAll();
  }

  @Test
  void testGetAll_Empty() {
    var categories = categoryDao.getAll();
    assertThat(categories).isEmpty();
  }

  @Test
  void testGetAll_WithData() {
    testData.loadCategories();
    var categories = categoryDao.getAll();
    assertThat(categories).hasSize(2);
    assertThat(categories.get(0).getName()).isEqualTo("Boxes");
    assertThat(categories.get(1).getName()).isEqualTo("Fruits");
  }

  @Test
  void testGetCategoryByName_Found() {
    testData.loadCategories();
    var categoryOpt = categoryDao.getCategoryByName("Boxes");
    assertThat(categoryOpt).isPresent();
    assertThat(categoryOpt.get().getName()).isEqualTo("Boxes");
  }

  @Test
  void testGetCategoryByName_NotFound() {
    testData.loadCategories();
    var categoryOpt = categoryDao.getCategoryByName("Machines");
    assertThat(categoryOpt).isEmpty();
  }

  @Test
  void testGetCategoryById_Found() {
    testData.loadCategories();
    var categoryOpt = categoryDao.getCategoryById(TestData.FRUITS_CATEGORY_ID);
    assertThat(categoryOpt).isPresent();
    assertThat(categoryOpt.get().getName()).isEqualTo("Fruits");
  }

  @Test
  void testGetCategoryById_NotFound() {
    testData.loadCategories();
    var categoryOpt = categoryDao.getCategoryById(UUID.randomUUID());
    assertThat(categoryOpt).isEmpty();
  }

  @Test
  void testCreateCategory() {
    categoryDao.createCategory("Boats");
    var results = categoryDao.getAll();
    assertThat(results).hasSize(1);
    assertThat(results.getFirst().getName()).isEqualTo("Boats");
  }
}
