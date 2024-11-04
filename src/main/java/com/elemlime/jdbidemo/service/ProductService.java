package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.ProductDao;
import com.elemlime.jdbidemo.model.Product;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDao productDao;
    
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }
    
    public List<Product> getAll() {
        return productDao.getAll();
    }
}
