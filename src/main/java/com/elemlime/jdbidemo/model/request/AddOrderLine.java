package com.elemlime.jdbidemo.model.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderLine {
  private UUID orderId;
  private UUID productId;
  private int quantity;
}
