package com.elemlime.jdbidemo.service.impl;

import com.elemlime.jdbidemo.dao.CustomerDao;
import com.elemlime.jdbidemo.dao.OrderDao;
import com.elemlime.jdbidemo.dao.ProductDao;
import com.elemlime.jdbidemo.exception.DBConsistencyException;
import com.elemlime.jdbidemo.exception.RowNotFoundException;
import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.model.dto.OrderLineDto;
import com.elemlime.jdbidemo.service.OrderService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderDao orderDao;
  private final CustomerDao customerDao;
  private final ProductDao productDao;

  public OrderServiceImpl(OrderDao orderDao, CustomerDao customerDao, ProductDao productDao) {
    this.orderDao = orderDao;
    this.customerDao = customerDao;
    this.productDao = productDao;
  }

  @Override
  public List<Order> listAll() {
    return orderDao.getAll();
  }

  @Override
  public Order findById(UUID orderId) {
    return orderDao
        .getById(orderId)
        .orElseThrow(
            () -> new RowNotFoundException(String.format("Order with id %s not found", orderId)));
  }

  @Override
  public UUID create(UUID customerId) {
    var customer =
        customerDao
            .getCustomerById(customerId)
            .orElseThrow(
                () ->
                    new RowNotFoundException(
                        String.format("Customer with id %s not found", customerId)));
    return orderDao.createOrder(customer.getId(), OrderStatus.CREATED);
  }

  @Override
  public void updateStatus(UUID orderId, OrderStatus status) {
    orderDao.updateOrderStatus(orderId, status);
  }

  @Override
  @Transactional
  public UUID addOrderLine(UUID orderId, UUID productId, int quantity) {
    var order =
        orderDao
            .getById(orderId)
            .orElseThrow(
                () ->
                    new RowNotFoundException(String.format("Order with id %s not found", orderId)));
    var product =
        productDao
            .getById(productId)
            .orElseThrow(
                () ->
                    new RowNotFoundException(
                        String.format("Product with id %s not found", productId)));
    if (product.getInventory() < quantity) {
      throw new IllegalArgumentException(
          String.format("Quantity %d exceeds inventory %d", quantity, product.getInventory()));
    }
    productDao.updateInventory(productId, product.getInventory() - quantity);
    var orderLine = new OrderLineDto(order.getId(), productId, quantity, product.getPrice());
    orderDao.updated(orderId);
    return orderDao.addOrderLine(orderLine);
  }

  @Override
  @Transactional
  public void deleteOrderLine(UUID orderLineId) {
    var orderLine =
        orderDao
            .getOrderLineById(orderLineId)
            .orElseThrow(
                () ->
                    new RowNotFoundException(
                        String.format("Order line with id %s not found", orderLineId)));
    var product =
        productDao
            .getById(orderLine.getProductId())
            .orElseThrow(
                () ->
                    new DBConsistencyException(
                        String.format(
                            "Product with id %s not found but specified in order line %s",
                            orderLine.getProductId(), orderLine.getId())));
    productDao.updateInventory(product.getId(), product.getInventory() + orderLine.getQuantity());
    orderDao.updated(orderLine.getOrderId());
    orderDao.deleteOrderLine(orderLineId);
  }

  @Override
  public void delete(UUID orderId) {
    orderDao.deleteOrder(orderId);
  }

  @Override
  public void deleteOrdersForCustomer(UUID customerId) {
    orderDao.deleteOrdersByCustomer(customerId);
  }
}
