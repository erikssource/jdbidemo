package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Customer;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterBeanMapper(Customer.class)
public interface CustomerDao {
    @SqlQuery("""
        SELECT id, first_name, last_name, email, created, updated
        FROM customer
        ORDER BY last_name, first_name
              """)
    public List<Customer> getAll();
}
