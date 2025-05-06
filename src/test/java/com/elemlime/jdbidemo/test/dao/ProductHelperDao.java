package com.elemlime.jdbidemo.test.dao;

import com.elemlime.jdbidemo.test.model.ProductTestDto;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ProductHelperDao {
  @SqlUpdate("DELETE FROM product")
  void deleteAll();

  @SqlBatch(
      """
      INSERT INTO product (id, name, description, category_id, price, inventory, created, updated)
      VALUES (:id, :name, :description, :categoryId, :price, :inventory, :now, :now)
      ON CONFLICT DO NOTHING
      """)
  @Timestamped
  void batchInsert(@BindMethods ProductTestDto... products);
}
