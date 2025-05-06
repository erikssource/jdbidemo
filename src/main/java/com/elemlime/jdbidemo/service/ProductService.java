package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {

  List<Product> listAll();

  Product findById(UUID productId);

  List<Product> listByCategory(UUID categoryId);

  UUID create(String name, String description, UUID categoryId, int price, int inventory);

  void update(
      UUID productId, String name, String description, UUID categoryId, int price, int inventory);

  void updatePrice(UUID productId, int price);

  void updateInventory(UUID productId, int inventory);
}
