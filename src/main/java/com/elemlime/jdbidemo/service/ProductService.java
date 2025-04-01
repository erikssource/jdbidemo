package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Product;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.sqlobject.customizer.BindBean;

public interface ProductService {

  List<Product> getAll();

  Product findProduct(UUID productId);

  UUID createProduct(String name, String description, UUID categoryId, int price, int inventory);

  void updateProduct(UUID productId, String name, String description, UUID categoryId, int price, int inventory);

  void updatePrice(UUID productId, int price);

  void updateInventory(UUID productId, int inventory);
}
