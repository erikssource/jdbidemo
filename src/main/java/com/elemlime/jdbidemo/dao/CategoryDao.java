package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import java.util.Optional;

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
        SELECT id, name, created, updated FROM category ORDER BY name DESC
        """)
  List<Category> getAll();

  @SqlQuery(
      """
        SELECT * FROM category WHERE name = :categoryName
        """)
  Optional<Category> getCategoryByName(@Bind("categoryName") String categoryName);

  @SqlQuery(
      """
                INSERT INTO  category (name, created, updated) VALUES (:categoryName, :now, :now)
                ON CONFLICT(name) DO NOTHING RETURNING *
            """)
  @GetGeneratedKeys
  @Timestamped
  Optional<Category> insertCategory(@Bind("categoryName") String categoryName);
}
