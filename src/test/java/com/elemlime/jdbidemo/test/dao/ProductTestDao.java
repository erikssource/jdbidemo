package com.elemlime.jdbidemo.test.dao;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ProductTestDao {
  @SqlUpdate("DELETE FROM product")
  void deleteAll();
}
