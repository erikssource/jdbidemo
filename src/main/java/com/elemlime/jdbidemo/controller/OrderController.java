package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }
}

