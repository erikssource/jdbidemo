package com.elemlime.jdbidemo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateCustomer {
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @Email
  private String email;
}
