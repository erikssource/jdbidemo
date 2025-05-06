package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

  List<Customer> listAll();

  Customer findById(UUID id);

  Customer create(String email, String firstName, String lastName);

  Customer update(UUID id, String email, String firstName, String lastName);

  void delete(UUID id);
}
