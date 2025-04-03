package com.elemlime.jdbidemo.test.dao;

import com.elemlime.jdbidemo.dao.CategoryDao;

public class TestData {
  public static void loadCategories(CategoryDao categoryDao) {
    categoryDao.createCategory("Boxes");
    categoryDao.createCategory("Fruits");
  }
}
