package com.elemlime.jdbidemo.service.impl;

import com.elemlime.jdbidemo.dao.CustomerDao;
import com.elemlime.jdbidemo.dao.OrderDao;
import com.elemlime.jdbidemo.model.Customer;
import com.elemlime.jdbidemo.service.CustomerService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerDao customerDao;
  private final OrderDao orderDao;

  public CustomerServiceImpl(CustomerDao customerDao, OrderDao orderDao) {
    this.customerDao = customerDao;
    this.orderDao = orderDao;
  }

  @Override
  public List<Customer> getAll() {
    return customerDao.getAll();
  }

  @Override
  public Customer findById(UUID id) {
    return customerDao
        .getCustomerById(id)
        .orElseThrow(
            () ->
                new HttpClientErrorException(
                    HttpStatus.NOT_FOUND, String.format("Customer with id %s not found", id)));
  }

  @Override
  public Customer createCustomer(String email, String firstName, String lastName) {
    return customerDao.createCustomer(email, firstName, lastName);
  }

  @Override
  public Customer updateCustomer(UUID id, String email, String firstName, String lastName) {
    return customerDao.updateCustomer(id, email, firstName, lastName);
  }

  @Transactional
  @Override
  public void deleteCustomer(UUID id) {
    orderDao.deleteOrdersByCustomer(id);
    customerDao.deleteCustomer(id);
  }
}
