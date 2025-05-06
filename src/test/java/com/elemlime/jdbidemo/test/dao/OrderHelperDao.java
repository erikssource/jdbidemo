package com.elemlime.jdbidemo.test.dao;

import com.elemlime.jdbidemo.test.model.OrderTestDto;
import com.elemlime.jdbidemo.test.model.OrderLineTestDto;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlScript;

public interface OrderHelperDao {
  @SqlScript("DELETE FROM orders")
  @SqlScript("DELETE FROM order_line")
  void deleteAll();

  @SqlBatch(
    """
    INSERT INTO orders (id, customer_id, status, created, updated)
    VALUES (:id, :customerId, :status, :now, :now)
    ON CONFLICT DO NOTHING
    """)
  @Timestamped
  void insertOrders(@BindBean List<OrderTestDto> orders);

  @SqlBatch(
    """
    INSERT INTO order_line (id, order_id, product_id, quantity, price, created, updated)
    VALUES (:id, :orderId, :productId, :quantity, :price, :now, :now)
    """)
  @Timestamped
  void insertOrderLines(@BindMethods List<OrderLineTestDto> orderLines);

  @SqlQuery("SELECT id FROM order_line")
  List<UUID> getAllOrderLinesIds();
}
