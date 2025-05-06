package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.model.Customer;
import com.elemlime.jdbidemo.model.request.CreateOrUpdateCustomer;
import com.elemlime.jdbidemo.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(final CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public List<Customer> listAll() {
    return customerService.listAll();
  }

  @GetMapping("/{id}")
  public Customer findById(@PathVariable UUID id) {
    return customerService.findById(id);
  }

  @PutMapping("/{id}")
  public Customer updateCustomer(
      @PathVariable UUID id, @Valid @RequestBody CreateOrUpdateCustomer customer) {
    return customerService.update(
        id, customer.getEmail(), customer.getFirstName(), customer.getLastName());
  }

  @PostMapping
  public Customer createCustomer(@Valid @RequestBody CreateOrUpdateCustomer customerData) {
    return customerService.create(
        customerData.getEmail(), customerData.getFirstName(), customerData.getLastName());
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable UUID id) {
    customerService.delete(id);
  }
}
