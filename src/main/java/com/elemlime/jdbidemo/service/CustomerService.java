package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.model.Customer;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerService {

  List<Customer> getAll();

  Customer findById(UUID id);

  Customer createCustomer(String email, String firstName, String lastName);

  Customer updateCustomer(UUID id, String email, String firstName, String lastName);

  void deleteCustomer(UUID id);
}
