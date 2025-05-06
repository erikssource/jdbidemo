package com.elemlime.jdbidemo.service.impl;

import com.elemlime.jdbidemo.dao.CustomerDao;
import com.elemlime.jdbidemo.dao.OrderDao;
import com.elemlime.jdbidemo.exception.RowNotFoundException;
import com.elemlime.jdbidemo.model.Customer;
import com.elemlime.jdbidemo.service.CustomerService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerDao customerDao;
  private final OrderDao orderDao;

  public CustomerServiceImpl(CustomerDao customerDao, OrderDao orderDao) {
    this.customerDao = customerDao;
    this.orderDao = orderDao;
  }

  @Override
  public List<Customer> listAll() {
    return customerDao.getAll();
  }

  @Override
  public Customer findById(UUID id) {
    return customerDao
        .getCustomerById(id)
        .orElseThrow(
            () -> new RowNotFoundException(String.format("Customer with id %s not found", id)));
  }

  @Override
  public Customer create(String email, String firstName, String lastName) {
    return customerDao.createCustomer(email, firstName, lastName);
  }

  @Override
  public Customer update(UUID id, String email, String firstName, String lastName) {
    return customerDao.updateCustomer(id, email, firstName, lastName);
  }

  @Transactional
  @Override
  public void delete(UUID id) {
    orderDao.deleteOrdersByCustomer(id);
    customerDao.deleteCustomer(id);
  }
}
