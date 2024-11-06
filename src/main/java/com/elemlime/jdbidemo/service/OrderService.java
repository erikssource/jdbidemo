package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.OrderDao;
import com.elemlime.jdbidemo.model.Order;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderDao orderDao;
    
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
