package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Category;
import java.util.List;

public interface CategoryService {

  List<Category> getAll();

  Category createCategory(String categoryName);
}
