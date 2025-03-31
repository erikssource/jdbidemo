package com.elemlime.jdbidemo.service.impl;

import com.elemlime.jdbidemo.dao.ProductDao;
import com.elemlime.jdbidemo.model.Product;
import com.elemlime.jdbidemo.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
    
    @Override public List<Product> getAll() {
        return productDao.getAll();
    }
}
