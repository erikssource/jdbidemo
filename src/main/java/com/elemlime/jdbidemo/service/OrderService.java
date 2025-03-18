package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.CustomerDao;
import com.elemlime.jdbidemo.dao.OrderDao;
import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.model.OrderStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class OrderService {
    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    
    public OrderService(OrderDao orderDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
    }
    
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    public Order findOrder(UUID orderId) {
        return orderDao.getById(orderId).orElseThrow(()
            -> new HttpClientErrorException(HttpStatus.NOT_FOUND,  String.format("Order with id %s not found", orderId)));
    }

    public UUID createOrder(String customerEmail) {
        var customer = customerDao.getCustomerByEmail(customerEmail).orElseThrow(() ->
            new HttpClientErrorException(HttpStatus.NOT_FOUND,  String.format("Customer with email %s not found", customerEmail)));
        return orderDao.createOrder(customer.getId(), OrderStatus.CREATED);
    }
}
