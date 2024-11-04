package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Customer;
import com.elemlime.jdbidemo.service.CustomerService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }
}
