package com.elemlime.jdbidemo.test.dao;

import java.util.List;
import java.util.UUID;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CustomerHelperDao {
  @SqlUpdate("DELETE FROM customer")
  void deleteAll();

  @SqlBatch(
      """
      INSERT INTO customer (id, email, first_name, last_name, created, updated)
      VALUES (:id, :email, :firstName, :lastName, :now, :now)
      ON CONFLICT DO NOTHING
      """)
  @Timestamped
  void batchInsert(
      @Bind("id") List<UUID> ids, @Bind("email") List<String> emails, @Bind("firstName") List<String> firstNames, @Bind("lastName") List<String> lastNames);
}
