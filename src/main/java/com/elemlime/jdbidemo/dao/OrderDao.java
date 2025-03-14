package com.elemlime.jdbidemo.dao;

import com.elemlime.jdbidemo.model.Customer;
import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.model.OrderLine;
import com.elemlime.jdbidemo.model.Product;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

@RegisterBeanMapper(value = Order.class, prefix = "o")
@RegisterBeanMapper(value = Customer.class, prefix = "c")
@RegisterBeanMapper(value = OrderLine.class, prefix = "l")
@RegisterBeanMapper(value = Product.class, prefix = "p")
public interface OrderDao {
  @SqlQuery(
      """
        SELECT o.id o_id, o.status o_status, l.id l_id, l.product_id l_product_id, p.name p_name, p.description p_description, l.quantity l_quantity, l.price l_price, o.created o_created, o.updated o_updated, c.id c_id, c.first_name c_firstName, c.last_name c_lastName, c.email c_email, c.created c_created, c.updated c_updated
        FROM orders o INNER JOIN customer c ON o.customer_id=c.id INNER JOIN order_line l ON o.id = l.order_id INNER JOIN product p ON l.product_id = p.id
        """)
  @UseRowReducer(CustomerReducer.class)
  List<Order> getAll();

  class CustomerReducer implements LinkedHashMapRowReducer<UUID, Order> {

    @Override
    public void accumulate(Map<UUID, Order> map, RowView rowView) {
      Order order =
          map.computeIfAbsent(
              rowView.getColumn("o_id", UUID.class), id -> rowView.getRow(Order.class));
      if (order.getCustomer() == null && rowView.getColumn("c_id", UUID.class) != null) {
        order.setCustomer(rowView.getRow(Customer.class));
      }
    }
  }
}
