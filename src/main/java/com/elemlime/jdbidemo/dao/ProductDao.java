package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Product;
import com.elemlime.jdbidemo.model.dto.ProductDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Product.class)
public interface ProductDao {
  String SELECT_FROM_PRODUCT_TABLE =
      """
     SELECT p.id, p.name, p.description, c.name AS category, p.price, p.inventory, p.created, p.updated
     FROM product p INNER JOIN category c ON p.category_id = c.id
   """;

  @SqlQuery(SELECT_FROM_PRODUCT_TABLE + " ORDER BY c.name, p.name")
  List<Product> getAll();

  @SqlQuery(SELECT_FROM_PRODUCT_TABLE + " WHERE p.category_id = :categoryId ORDER BY p.name")
  List<Product> getByCategory(@Bind("categoryId") UUID id);

  @SqlQuery(SELECT_FROM_PRODUCT_TABLE + " WHERE p.id = :productId")
  Optional<Product> getById(@Bind("productId") UUID id);

  @SqlUpdate("""
      INSERT INTO product (name, description, category_id, price, inventory, created, updated)
      VALUES (:name, :description, :categoryId, :price, :inventory, :now, :now)
      """)
  @Timestamped
  @GetGeneratedKeys("id")
  UUID createProduct(@BindBean ProductDto product);

  @SqlUpdate("""
      UPDATE product SET category_id = :categoryId, name = :name, description = :description, price = :price, inventory = :inventory, updated = :now
      WHERE id = :productId
      """)
  @Timestamped
  void updateProduct(@Bind UUID productId, @BindBean ProductDto product);

  @SqlUpdate("""
    UPDATE product SET price = :price, updated = :now WHERE id = :productId
    """)
  @Timestamped
  void updatePrice(@Bind UUID productId, @Bind int price);

  @SqlUpdate("""
    UPDATE product SET inventory = :inventory, updated = :now WHERE id = :productId
    """)
  @Timestamped
  void updateInventory(@Bind UUID productId, @Bind int inventory);
}
