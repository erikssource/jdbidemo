package com.elemlime.jdbidemo.service.impl;

import com.elemlime.jdbidemo.dao.ProductDao;
import com.elemlime.jdbidemo.exception.RowNotFoundException;
import com.elemlime.jdbidemo.model.Product;
import com.elemlime.jdbidemo.model.dto.ProductDto;
import com.elemlime.jdbidemo.service.ProductService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductDao productDao;

  public ProductServiceImpl(ProductDao productDao) {
    this.productDao = productDao;
  }

  @Override
  public List<Product> listAll() {
    return productDao.getAll();
  }

  @Override
  public Product findById(UUID productId) {
    return productDao
        .getById(productId)
        .orElseThrow(
            () ->
                new RowNotFoundException(
                    String.format("Product with id %s not found", productId)));
  }

  @Override
  public List<Product> listByCategory(UUID categoryId) {
    return productDao.getByCategory(categoryId);
  }

  @Override
  public UUID create(String name, String description, UUID categoryId, int price, int inventory) {
    var productDto = new ProductDto();
    productDto.setName(name);
    productDto.setDescription(description);
    productDto.setCategoryId(categoryId);
    productDto.setPrice(price);
    productDto.setInventory(inventory);
    return productDao.createProduct(productDto);
  }

  @Override
  public void update(
      UUID productId, String name, String description, UUID categoryId, int price, int inventory) {
    var productDto = new ProductDto();
    productDto.setName(name);
    productDto.setDescription(description);
    productDto.setCategoryId(categoryId);
    productDto.setPrice(price);
    productDto.setInventory(inventory);

    productDao.updateProduct(productId, productDto);
  }

  @Override
  public void updatePrice(UUID productId, int price) {
    productDao.updatePrice(productId, price);
  }

  @Override
  public void updateInventory(UUID productId, int inventory) {
    productDao.updateInventory(productId, inventory);
  }
}
