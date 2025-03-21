package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Customer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
      SELECT id, first_name, last_name, email, created, updated FROM customer WHERE id = :id
      """)
  Optional<Customer> getCustomerById(@Bind UUID id);

  @SqlUpdate(
      """
      UPDATE customer SET first_name = :firstName, last_name = :lastName, email = :email, updated = :now
      WHERE id = :id
      RETURNING *
      """)
  @GetGeneratedKeys
  @Timestamped
  Customer updateCustomer(@Bind UUID id, @Bind String email, @Bind String firstName, @Bind String lastName);

  @SqlUpdate(
      """
      INSERT INTO customer (first_name, last_name, email, created, updated)
      VALUES  (:firstName, :lastName, :email, :now, :now)
      RETURNING *
      """)
  @GetGeneratedKeys
  @Timestamped
  Customer createCustomer(@Bind String email, @Bind String firstName, @Bind String lastName);

  @SqlUpdate(
    """
    DELETE FROM customer WHERE id = :id
    """
  )
  void deleteCustomer(@Bind UUID id);
}
