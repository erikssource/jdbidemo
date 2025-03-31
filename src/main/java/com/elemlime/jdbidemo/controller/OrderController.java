package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.model.request.AddOrderLine;
import com.elemlime.jdbidemo.model.request.CreateOrder;
import com.elemlime.jdbidemo.model.response.ResponseId;
import com.elemlime.jdbidemo.service.OrderService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/{orderId}")
  public Order getOne(@PathVariable("orderId") UUID orderId) {
    return orderService.findOrder(orderId);
  }

  @PostMapping
  public ResponseId create(@RequestBody CreateOrder createOrder) {
    var id = orderService.createOrder(createOrder.getCustomerId());
    return new ResponseId(id);
  }

  @PostMapping("/{orderId}/line")
  public ResponseId addOrderLine(@RequestBody AddOrderLine addOrderLine,
                                 @PathVariable("orderId") UUID orderId) {
    var id = orderService.addOrderLine(orderId, addOrderLine.getProductId(), addOrderLine.getQuantity());
    return new ResponseId(id);
  }

  @DeleteMapping("/{orderId}")
  public void deleteOrder(@PathVariable("orderId") UUID orderId) {
    orderService.deleteOrder(orderId);
  }
}
