package com.elemlime.jdbidemo.test.dao;

import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ProductTestDao {
  @SqlUpdate("DELETE FROM product")
  void deleteAll();

  @SqlBatch(
      """
      INSERT INTO product (id, name, description, category_id, price, inventory, created, updated)
      VALUES (:name, :description, :categoryId, :price, :inventory, :now, :now)
      ON CONFLICT DO NOTHING
      """)
  @Timestamped
  void batchInsert(@BindMethods ProductTestDto... products);
}
