package com.elemlime.jdbidemo.service;

import com.elemlime.jdbidemo.dao.CustomerDao;
import com.elemlime.jdbidemo.model.Customer;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    public List<Customer> getAll() {
        return customerDao.getAll();
    }
}
