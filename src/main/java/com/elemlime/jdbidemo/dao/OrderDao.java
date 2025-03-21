package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Customer;
import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.model.OrderLine;
import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.model.dto.OrderLineDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlCall;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

@RegisterBeanMapper(value = Order.class, prefix = "o")
@RegisterBeanMapper(value = Customer.class, prefix = "c")
@RegisterBeanMapper(value = OrderLine.class, prefix = "l")
public interface OrderDao {
  String ORDER_SELECT = """
      SELECT o.id o_id, o.status o_status, l.id l_id, l.product_id l_product_id, p.name p_name,
               p.description p_description, l.order_id l_order_id, l.quantity l_quantity, l.price l_price,
               l.created l_created, l.updated l_updated, o.created o_created,
               o.updated o_updated, c.id c_id, c.first_name c_firstName, c.last_name c_lastName,
               c.email c_email, c.created c_created, c.updated c_updated
      FROM orders o INNER JOIN customer c ON o.customer_id=c.id INNER JOIN order_line l ON o.id = l.order_id INNER JOIN product p ON l.product_id = p.id
    """;

  @SqlQuery(ORDER_SELECT)
  @UseRowReducer(OrderReducer.class)
  List<Order> getAll();

  @SqlQuery(ORDER_SELECT + " WHERE o.id = :id")
  @UseRowReducer(OrderReducer.class)
  Optional<Order> getById(@Bind UUID id);

  @SqlUpdate("INSERT INTO orders (customer_id, status, created, updated) VALUES (:customerId, :status, :now, :now)")
  @Timestamped
  @GetGeneratedKeys("id")
  UUID createOrder(@Bind UUID customerId, @Bind OrderStatus status);

  @SqlUpdate("UPDATE orders SET status=:status, updated=:now WHERE id = :id")
  @Timestamped
  void updateOrderStatus(@Bind UUID id, @Bind OrderStatus status);

  @SqlUpdate("UPDATE orders SET updated=:now WHERE id = :id")
  @Timestamped
  void updateOrder(@Bind UUID id);

  @SqlUpdate("""
    INSERT INTO order_line (order_id, product_id, quantity, price, created, updated)
    VALUES (:orderId, :productId, :quantity, :price, :now, :now)
    """)
  @Timestamped
  @GetGeneratedKeys("id")
  UUID addOrderLine(@BindMethods OrderLineDto orderLineDto);

  @SqlCall("{call delete_order_by_id(:orderId)}")
  void deleteOrder(@Bind UUID orderId);

  @SqlCall("{call delete_orders_by_customer(:customerId)}")
  void deleteOrdersByCustomer(@Bind UUID customerId);

  class OrderReducer implements LinkedHashMapRowReducer<UUID, Order> {

    @Override
    public void accumulate(Map<UUID, Order> map, RowView rowView) {
      Order order =
          map.computeIfAbsent(
              rowView.getColumn("o_id", UUID.class), id -> rowView.getRow(Order.class));
      if (order.getCustomer() == null && rowView.getColumn("c_id", UUID.class) != null) {
        order.setCustomer(rowView.getRow(Customer.class));
      }
      if (rowView.getColumn("l_id", UUID.class) != null) {
        var orderLine = rowView.getRow(OrderLine.class);
        orderLine.setName(rowView.getColumn("p_name", String.class));
        orderLine.setDescription(rowView.getColumn("p_description", String.class));
        order.getOrderLines().add(orderLine);
      }
    }
  }
}
