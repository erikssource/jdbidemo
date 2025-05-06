package com.elemlime.jdbidemo.test.model;

import com.elemlime.jdbidemo.model.OrderStatus;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTestDto {
  private UUID id;
  private UUID customerId;
  private OrderStatus status;
}
