package com.elemlime.jdbidemo.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategory {
  @NotBlank(message = "Category Name is Required")
  private String name;
}
