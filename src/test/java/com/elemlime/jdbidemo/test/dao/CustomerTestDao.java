package com.elemlime.jdbidemo.test.dao;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CustomerTestDao {
  @SqlUpdate("DELETE FROM customer")
  void deleteAll();
}
