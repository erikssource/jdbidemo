package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.CustomerDao;
import com.elemlime.jdbidemo.dao.OrderDao;
import com.elemlime.jdbidemo.dao.ProductDao;
import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.model.dto.OrderLineDto;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class OrderService {
  private final OrderDao orderDao;
  private final CustomerDao customerDao;
  private final ProductDao productDao;

  public OrderService(OrderDao orderDao,
                      CustomerDao customerDao,
                      ProductDao productDao) {
    this.orderDao = orderDao;
    this.customerDao = customerDao;
    this.productDao = productDao;
  }

  public List<Order> getAll() {
    return orderDao.getAll();
  }

  public Order findOrder(UUID orderId) {
    return orderDao
        .getById(orderId)
        .orElseThrow(
            () ->
                new HttpClientErrorException(
                    HttpStatus.NOT_FOUND, String.format("Order with id %s not found", orderId)));
  }

  public UUID createOrder(UUID customerId) {
    var customer =
        customerDao
            .getCustomerById(customerId)
            .orElseThrow(
                () ->
                    new HttpClientErrorException(
                        HttpStatus.NOT_FOUND,
                        String.format("Customer with id %s not found", customerId)));
    return orderDao.createOrder(customer.getId(), OrderStatus.CREATED);
  }

  public UUID addOrderLine(UUID orderId, UUID productId, int quantity) {
    var order =
        orderDao
            .getById(orderId)
            .orElseThrow(
                () ->
                    new HttpClientErrorException(
                        HttpStatus.NOT_FOUND,
                        String.format("Order with id %s not found", orderId)));
    // TODO: Remove quantity from product inventory and make transactional
    var product = productDao.getById(productId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Product with id %s not found", productId)));
    var orderLine = new OrderLineDto(order.getId(), productId, quantity, product.getPrice());
    return orderDao.addOrderLine(orderLine);
  }

  public void deleteOrder(UUID orderId) {
    orderDao.deleteOrder(orderId);
  }
}
