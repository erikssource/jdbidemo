package com.elemlime.jdbidemo.dao;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assertions.assertThat;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.test.dao.TestData;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TestcontainersConfiguration.class, TestDaoConfig.class})
@SpringBootTest
public class OrderDaoTest extends AbstractDaoTest {
  @Autowired
  private OrderDao orderDao;
  @Autowired
  private TestData testData;

  @BeforeEach
  void setUp() {
    testData.deleteAll();
  }

  @Test
  void testGetAll_Empty() {
    var result = orderDao.getAll();
    assertThat(result).isEmpty();
  }

  @Test
  void testGetAll_WithData() {
    testData.loadOrders();
    var result = orderDao.getAll();
    assertThat(result).hasSize(2);
    verifyOrder1(result.get(0));
    verifyOrder2(result.get(1));
  }

  @Test
  void testGetById_Order1() {
    testData.loadOrders();
    var result = orderDao.getById(TestData.ORDER_1_ID);
    assertThat(result).isPresent();
    verifyOrder1(result.get());
  }

  @Test
  void testGetById_Order2() {
    testData.loadOrders();
    var result = orderDao.getById(TestData.ORDER_2_ID);
    assertThat(result).isPresent();
    verifyOrder2(result.get());
  }

  @Test
  void testGetById_NotFound() {
    testData.loadOrders();
    var result = orderDao.getById(UUID.randomUUID());
    assertThat(result).isEmpty();
  }

  @Test
  void testCreateOrder() {
    testData.loadOrders();
    var id = orderDao.createOrder(TestData.JDOE_CUSTOMER_ID, OrderStatus.CREATED);
    assertThat(id).isNotNull();

    var result = orderDao.getById(id);
    assertThat(result).isPresent();
    var order = result.get();
    assertThat(order.getId()).isEqualTo(id);
    assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
    assertThat(order.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(order.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));

    assertThat(order.getOrderLines()).isEmpty();

    verifyCustomerJDoe(order.getCustomer());
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
