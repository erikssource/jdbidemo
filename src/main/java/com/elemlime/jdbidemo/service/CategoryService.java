package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

  List<Category> getAll();

  Category getById(UUID id);

  Category createCategory(String categoryName);
}
