package com.elemlime.jdbidemo.model.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
  private String name;
  private String description;
  private UUID categoryId;
  private int price;
  private int inventory;
}
