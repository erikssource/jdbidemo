package com.elemlime.jdbidemo.dao;

import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.test.dao.TestData;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TestcontainersConfiguration.class, TestDaoConfig.class})
@SpringBootTest
public class OrderDaoTest {
  @Autowired
  private OrderDao orderDao;
  @Autowired
  private TestData testData;

  @BeforeEach
  void setUp() {
    testData.deleteAll();
  }

  void testGetAll_Empty() {
    var result = orderDao.getAll();
    assertTrue(result.isEmpty());
  }

  @Test
  void testGetAll_WithData() {
    testData.loadOrders();
    var result = orderDao.getAll();
    assertThat(result).hasSize(2);
    var order1 = result.getFirst();
    assertThat(order1.getId()).isEqualTo(TestData.ORDER_1_ID);
    assertThat(order1.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    assertThat(order1.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(order1.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));

    var customer = order1.getCustomer();
    assertThat(customer.getEmail()).isEqualTo("jdoe@example.com");
    assertThat(customer.getId()).isEqualTo(TestData.JDOE_CUSTOMER_ID);
    assertThat(customer.getFirstName()).isEqualTo("John");
    assertThat(customer.getLastName()).isEqualTo("Doe");
    assertThat(customer.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(customer.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));

    assertThat(order1.getOrderLines()).hasSize(2);

    var orderLine1 = order1.getOrderLines().getFirst();
    assertThat(orderLine1.getId()).isEqualTo(TestData.ORDER_1_LINE_1_ID);

    var orderLine2 = result.get(0).getOrderLines().get(1);
    assertThat(orderLine2.getId()).isEqualTo(TestData.ORDER_1_LINE_2_ID);

    var order2 = result.get(1);
    assertThat(order2.getId()).isEqualTo(TestData.ORDER_2_ID);
  }

  void testCreateOrder() {

  }

  void testUpdateOrderStatus() {

  }

  void testAddOrderLine() {

  }

  void testDeleteOrder() {

  }

  void testDeleteOrdersByCustomer() {

  }
}
