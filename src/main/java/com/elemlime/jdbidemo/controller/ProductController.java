package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Product;
import com.elemlime.jdbidemo.model.request.CreateOrUpdateProduct;
import com.elemlime.jdbidemo.model.request.UpdateInventory;
import com.elemlime.jdbidemo.model.request.UpdatePrice;
import com.elemlime.jdbidemo.model.response.ResponseId;
import com.elemlime.jdbidemo.service.ProductService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")
    public Product getById(@PathVariable UUID id) {
        return productService.findProduct(id);
    }

    @PostMapping
    public ResponseId addProduct(@RequestBody CreateOrUpdateProduct product) {
        var id = productService.createProduct(product.getName(), product.getDescription(),
            product.getCategoryId(), product.getPrice(), product.getInventory());
        return new ResponseId(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable UUID id, @RequestBody CreateOrUpdateProduct product) {
        productService.updateProduct(id, product.getName(), product.getDescription(),
            product.getCategoryId(), product.getPrice(), product.getInventory());
    }

    @PatchMapping("/{id}")
    public void updatePrice(@PathVariable UUID id, @RequestBody UpdatePrice updatePrice) {
        productService.updatePrice(id, updatePrice.getPrice());
    }

    @PatchMapping("/{id}")
    public void updateInventory(@PathVariable UUID id, @RequestBody UpdateInventory updateInventory) {
        productService.updateInventory(id, updateInventory.getInventory());
    }
}
