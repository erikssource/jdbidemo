package com.elemlime.jdbidemo.dao;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assertions.assertThat;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.model.dto.OrderLineDto;
import com.elemlime.jdbidemo.test.dao.OrderHelperDao;
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
  @Autowired
  private OrderHelperDao orderHelperDao;

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
    var orderOpt = orderDao.getById(TestData.ORDER_2_ID);
    assertThat(orderOpt).isPresent();
    verifyOrder2(orderOpt.get());
  }

  @Test
  void testGetById_NotFound() {
    testData.loadOrders();
    var result = orderDao.getById(UUID.randomUUID());
    assertThat(result).isEmpty();
  }

  @Test
  void testGetOrderLineById_NotFound() {
    testData.loadOrders();
    var result = orderDao.getOrderLineById(UUID.randomUUID());
    assertThat(result).isEmpty();
  }

  @Test
  void testGetOrderLineById_Order1Line1() {
    testData.loadOrders();
    var orderLineOpt = orderDao.getOrderLineById(TestData.ORDER_1_LINE_1_ID);
    assertThat(orderLineOpt).isPresent();
    var orderLine = orderLineOpt.get();
    assertThat(orderLine.getId()).isEqualTo(TestData.ORDER_1_LINE_1_ID);
    assertThat(orderLine.getOrderId()).isEqualTo(TestData.ORDER_1_ID);
    assertThat(orderLine.getProductId()).isEqualTo(TestData.APPLE_PRODUCT_ID);
    assertThat(orderLine.getQuantity()).isEqualTo(6);
    assertThat(orderLine.getPrice()).isEqualTo(125);
  }

  @Test
  void testCreateOrder() {
    testData.loadOrders();
    var id = orderDao.createOrder(TestData.JDOE_CUSTOMER_ID, OrderStatus.CREATED);
    assertThat(id).isNotNull();

    var orderOpt = orderDao.getById(id);
    assertThat(orderOpt).isPresent();
    var order = orderOpt.get();
    assertThat(order.getId()).isEqualTo(id);
    assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
    assertThat(order.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(order.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));

    assertThat(order.getOrderLines()).isEmpty();

    verifyCustomerJDoe(order.getCustomer());
  }

  @Test
  void testAddOrderLine() {
    testData.loadOrders();
    var newOrderLine = new OrderLineDto(TestData.ORDER_1_ID, TestData.BIG_BOX_PRODUCT_ID, 2, 349);
    var id = orderDao.addOrderLine(newOrderLine);
    assertThat(id).isNotNull();
    var result = orderDao.getById(TestData.ORDER_1_ID);
    assertThat(result).isPresent();
    var orderLines = result.get().getOrderLines();
    assertThat(orderLines).hasSize(3);
    var orderLineOpt = orderLines.stream().filter(ol -> ol.getId().equals(id)).findFirst();
    assertThat(orderLineOpt).isPresent();
    var orderLine = orderLineOpt.get();
    assertThat(orderLine.getId()).isEqualTo(id);
    assertThat(orderLine.getQuantity()).isEqualTo(2);
    assertThat(orderLine.getPrice()).isEqualTo(349);
    assertThat(orderLine.getProductId()).isEqualTo(TestData.BIG_BOX_PRODUCT_ID);
  }

  @Test
  void testDeleteOrderLine() {
    testData.loadOrders();
    orderDao.deleteOrderLine(TestData.ORDER_1_LINE_1_ID);
    var order = orderDao.getById(TestData.ORDER_1_ID).orElseThrow();
    var orderLines = order.getOrderLines();
    assertThat(orderLines).hasSize(1);
    assertThat(orderLines.getFirst().getId()).isEqualTo(TestData.ORDER_1_LINE_2_ID);
  }

  @Test
  void testDeleteOrder() {
    testData.loadOrders();
    var newOrderLine = new OrderLineDto(TestData.ORDER_2_ID, TestData.BIG_BOX_PRODUCT_ID, 2, 349);
    var orderLineId = orderDao.addOrderLine(newOrderLine);
    orderDao.deleteOrder(TestData.ORDER_1_ID);
    var order1 = orderDao.getById(TestData.ORDER_1_ID);
    assertThat(order1).isEmpty();
    var order2 = orderDao.getById(TestData.ORDER_2_ID);
    assertThat(order2).isPresent();
    var orderLineIds = orderHelperDao.getAllOrderLinesIds();
    assertThat(orderLineIds).hasSize(1);
    assertThat(orderLineIds.getFirst()).isEqualTo(orderLineId);
  }

  @Test
  void testDeleteOrdersByCustomer() {
    testData.loadOrders();
    var newOrderLine = new OrderLineDto(TestData.ORDER_2_ID, TestData.BIG_BOX_PRODUCT_ID, 2, 349);
    var orderLineId = orderDao.addOrderLine(newOrderLine);
    orderDao.deleteOrdersByCustomer(TestData.JDOE_CUSTOMER_ID);
    var order1 = orderDao.getById(TestData.ORDER_1_ID);
    assertThat(order1).isEmpty();
    var order2 = orderDao.getById(TestData.ORDER_2_ID);
    assertThat(order2).isPresent();
    var orderLineIds = orderHelperDao.getAllOrderLinesIds();
    assertThat(orderLineIds).hasSize(1);
    assertThat(orderLineIds.getFirst()).isEqualTo(orderLineId);
  }
}
