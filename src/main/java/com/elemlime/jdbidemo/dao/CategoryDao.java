package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Category.class)
public interface CategoryDao {
  @SqlQuery(
      """
        SELECT id, name, created, updated FROM category ORDER BY name
        """)
  List<Category> getAll();

  @SqlQuery(
      """
      SELECT * FROM category WHERE id = :categoryId
      """)
  Optional<Category> getCategoryById(@Bind UUID categoryId);

  @SqlQuery(
      """
        SELECT * FROM category WHERE name = :categoryName
        """)
  Optional<Category> getCategoryByName(@Bind("categoryName") String categoryName);

  @SqlUpdate(
      """
        INSERT INTO  category (name, created, updated) VALUES (:categoryName, :now, :now)
        RETURNING *
        """)
  @GetGeneratedKeys
  @Timestamped
  Category createCategory(@Bind("categoryName") String categoryName);
}
