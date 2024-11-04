package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Product;
import com.elemlime.jdbidemo.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }
    
}
