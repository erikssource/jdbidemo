package com.elemlime.jdbidemo.test.dao;

import java.util.List;
import java.util.UUID;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CategoryHelperDao {
  @SqlUpdate("DELETE FROM category")
  void deleteAll();

  @SqlBatch("""
    INSERT INTO category (id, name, created, updated) VALUES (:id, :name, :now, :now)
    ON CONFLICT DO NOTHING
    """)
  @Timestamped
  void batchInsert(@Bind("id") List<UUID> ids, @Bind("name") List<String> names);
}
