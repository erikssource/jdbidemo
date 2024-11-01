package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Category;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterBeanMapper(Category.class)
public interface CategoryDao {
    @SqlQuery("""
        SELECT id, name, created, updated FROM category ORDER BY name DESC
        """)
    List<Category> getAll();
}
