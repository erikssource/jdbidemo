package com.elemlime.jdbidemo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.test.dao.TestData;
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
    assertEquals(2, result.size());
    assertEquals(TestData.ORDER_1_ID, result.get(0).getId());
    assertEquals(OrderStatus.COMPLETED, result.get(0).getStatus());

    System.out.println("Created: " + result.get(0).getCreated());

    assertEquals(TestData.ORDER_2_ID, result.get(1).getId());
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
