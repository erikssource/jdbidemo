package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.model.OrderStatus;
import java.util.List;
import java.util.UUID;

public interface OrderService {

  List<Order> listAll();

  Order findById(UUID orderId);

  UUID create(UUID customerId);

  void updateStatus(UUID orderId, OrderStatus status);

  UUID addOrderLine(UUID orderId, UUID productId, int quantity);

  void deleteOrderLine(UUID orderLineId);

  void delete(UUID orderId);

  void deleteOrdersForCustomer(UUID customerId);
}
