package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Order;
import java.util.List;
import java.util.UUID;

public interface OrderService {

  List<Order> getAll();

  Order findOrder(UUID orderId);

  UUID createOrder(UUID customerId);

  UUID addOrderLine(UUID orderId, UUID productId, int quantity);

  void deleteOrder(UUID orderId);
}
