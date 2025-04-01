package com.elemlime.jdbidemo.service.impl;

import com.elemlime.jdbidemo.dao.ProductDao;
import com.elemlime.jdbidemo.model.Product;
import com.elemlime.jdbidemo.model.dto.ProductDto;
import com.elemlime.jdbidemo.service.ProductService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
    
    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product findProduct(UUID productId) {
        return productDao.getById(productId).orElseThrow(() ->
            new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Product with id %s not found", productId)));
    }

    @Override
    public UUID createProduct(String name,
                              String description,
                              UUID categoryId,
                              int price,
                              int inventory) {
        var productDto = new ProductDto();
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setCategoryId(categoryId);
        productDto.setPrice(price);
        productDto.setInventory(inventory);
        return productDao.createProduct(productDto);
    }

    @Override
    public void updateProduct(UUID productId,
                              String name,
                              String description,
                              UUID categoryId,
                              int price,
                              int inventory) {
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
