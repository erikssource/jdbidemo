package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.CustomerDao;
import com.elemlime.jdbidemo.dao.OrderDao;
import com.elemlime.jdbidemo.model.Customer;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class CustomerService {
  private final CustomerDao customerDao;
  private final OrderDao orderDao;

  public CustomerService(CustomerDao customerDao, OrderDao orderDao) {
    this.customerDao = customerDao;
    this.orderDao = orderDao;
  }

  public List<Customer> getAll() {
    return customerDao.getAll();
  }

  public Customer findById(UUID id) {
    return customerDao
        .getCustomerById(id)
        .orElseThrow(
            () ->
                new HttpClientErrorException(
                    HttpStatus.NOT_FOUND, String.format("Customer with id %s not found", id)));
  }

  public Customer createCustomer(String email, String firstName, String lastName) {
    return customerDao.createCustomer(email, firstName, lastName);
  }

  public Customer updateCustomer(UUID id, String email, String firstName, String lastName) {
    return customerDao.updateCustomer(id, email, firstName, lastName);
  }

  @Transactional
  public void deleteCustomer(UUID id) {
    orderDao.deleteOrdersByCustomer(id);
    customerDao.deleteCustomer(id);
  }
}
