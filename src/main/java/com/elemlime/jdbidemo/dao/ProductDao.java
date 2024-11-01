package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Product;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterBeanMapper(Product.class)
public interface ProductDao {
    @SqlQuery("""
        SELECT p.id, p.name, p.description, c.name AS category, p.price, p.inventory, p.created, p.updated
        FROM product p INNER JOIN category c ON p.category_id = c.id
        ORDER BY p.name
              """)
    public List<Product> getAll();
}
