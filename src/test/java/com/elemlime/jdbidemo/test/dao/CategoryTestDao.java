package com.elemlime.jdbidemo.test.dao;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CategoryTestDao {
  @SqlUpdate("DELETE FROM category")
  void clearAll();
}
