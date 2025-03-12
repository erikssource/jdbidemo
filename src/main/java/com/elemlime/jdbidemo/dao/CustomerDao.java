package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Customer;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Customer.class)
public interface CustomerDao {
  @SqlQuery(
      """
        SELECT id, first_name, last_name, email, created, updated
        FROM customer
        ORDER BY last_name, first_name
       """)
  List<Customer> getAll();

  @SqlQuery(
      """
      SELECT id, first_name, last_name, email, created, updated FROM customer WHERE email = :email
      """)
  Optional<Customer> getCustomerByEmail(@Bind String email);

  @SqlUpdate(
      """
      INSERT INTO customer (first_name, last_name, email, created, updated)
            VALUES  (:firstName, :lastName, :email, :now, :now)
      ON CONFLICT (email) DO UPDATE SET last_name = :lastName, first_name = :firstName, updated = :now
            RETURNING *
      """)
  @GetGeneratedKeys
  @Timestamped
  Optional<Customer> createOrUpdateCustomer(
      @Bind String firstName, @Bind String lastName, @Bind String email);
}
