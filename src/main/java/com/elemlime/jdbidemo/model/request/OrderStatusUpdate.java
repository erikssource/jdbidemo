package com.elemlime.jdbidemo.model.request;

import com.elemlime.jdbidemo.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdate {
  private OrderStatus orderStatus;
}
