package com.elemlime.jdbidemo.test.dao;

import org.jdbi.v3.sqlobject.statement.SqlScript;

public interface OrderTestDao {
  @SqlScript("DELETE FROM orders")
  @SqlScript("DELETE FROM order_line")
  void deleteAll();
}
