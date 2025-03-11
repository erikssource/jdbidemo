package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Category.class)
public interface CategoryDao {
    @SqlQuery("""
        SELECT id, name, created, updated FROM category ORDER BY name DESC
        """)
    List<Category> getAll();

    @SqlUpdate("""
            INSERT INTO category (name, created, updated) VALUES (:name, now(), now()) RETURNING *
            """)
    Category insertCategory(@Bind("categoryName") String categoryName);
}
