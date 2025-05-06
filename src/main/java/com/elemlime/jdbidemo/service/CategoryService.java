package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import java.util.UUID;

public interface CategoryService {

  List<Category> listAll();

  Category findById(UUID id);

  Category findByName(String name);

  Category create(String categoryName);
}
