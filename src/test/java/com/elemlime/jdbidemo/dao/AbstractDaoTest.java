package com.elemlime.jdbidemo.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import com.elemlime.jdbidemo.model.Customer;
import com.elemlime.jdbidemo.model.Order;
import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.model.Product;
import com.elemlime.jdbidemo.test.dao.TestData;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public abstract class AbstractDaoTest {
  void verifyOrder1(Order order) {
    assertThat(order.getId()).isEqualTo(TestData.ORDER_1_ID);
    assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    assertThat(order.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(order.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));

    verifyCustomerJDoe(order.getCustomer());

    assertThat(order.getOrderLines()).hasSize(2);

    var orderLine1 = order.getOrderLines().getFirst();
    assertThat(orderLine1.getId()).isEqualTo(TestData.ORDER_1_LINE_1_ID);
    assertThat(orderLine1.getOrderId()).isEqualTo(TestData.ORDER_1_ID);
    assertThat(orderLine1.getProductId()).isEqualTo(TestData.APPLE_PRODUCT_ID);
    assertThat(orderLine1.getName()).isEqualTo("Apple");
    assertThat(orderLine1.getDescription()).isEqualTo("A tasty red apple");
    assertThat(orderLine1.getPrice()).isEqualTo(125);
    assertThat(orderLine1.getQuantity()).isEqualTo(6);
    assertThat(orderLine1.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(orderLine1.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));

    var orderLine2 = order.getOrderLines().get(1);
    assertThat(orderLine2.getId()).isEqualTo(TestData.ORDER_1_LINE_2_ID);
    assertThat(orderLine2.getOrderId()).isEqualTo(TestData.ORDER_1_ID);
    assertThat(orderLine2.getProductId()).isEqualTo(TestData.SMALL_BOX_PRODUCT_ID);
    assertThat(orderLine2.getName()).isEqualTo("Box, Small");
    assertThat(orderLine2.getDescription()).isEqualTo("A nice small box");
    assertThat(orderLine2.getPrice()).isEqualTo(299);
    assertThat(orderLine2.getQuantity()).isEqualTo(2);
    assertThat(orderLine2.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(orderLine2.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
  }

  void verifyOrder2(Order order) {
    assertThat(order.getId()).isEqualTo(TestData.ORDER_2_ID);
    assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
    assertThat(order.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(order.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));

    verifyCustomerMSmith(order.getCustomer());

    assertThat(order.getOrderLines()).hasSize(0);
  }

  void verifyCustomerJDoe(Customer customer) {
    assertThat(customer.getEmail()).isEqualTo("jdoe@example.com");
    assertThat(customer.getId()).isEqualTo(TestData.JDOE_CUSTOMER_ID);
    assertThat(customer.getFirstName()).isEqualTo("John");
    assertThat(customer.getLastName()).isEqualTo("Doe");
    assertThat(customer.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(customer.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
  }

  void verifyCustomerMSmith(Customer customer) {
    assertThat(customer.getEmail()).isEqualTo("msmith@example.com");
    assertThat(customer.getId()).isEqualTo(TestData.MSMITH_CUSTOMER_ID);
    assertThat(customer.getFirstName()).isEqualTo("Mary");
    assertThat(customer.getLastName()).isEqualTo("Smith");
    assertThat(customer.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(customer.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
  }

  void verifyProductApple(Product product) {
    assertThat(product.getId()).isEqualTo(TestData.APPLE_PRODUCT_ID);
    assertThat(product.getCategory()).isEqualTo("Fruits");
    assertThat(product.getName()).isEqualTo("Apple");
    assertThat(product.getDescription()).isEqualTo("A tasty red apple");
    assertThat(product.getPrice()).isEqualTo(125);
    assertThat(product.getInventory()).isEqualTo(50);
    assertThat(product.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(product.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
  }

  void verifyProductPear(Product product) {
    assertThat(product.getId()).isEqualTo(TestData.PEAR_PRODUCT_ID);
    assertThat(product.getCategory()).isEqualTo("Fruits");
    assertThat(product.getName()).isEqualTo("Pear");
    assertThat(product.getDescription()).isEqualTo("A mostly good pear");
    assertThat(product.getPrice()).isEqualTo(119);
    assertThat(product.getInventory()).isEqualTo(144);
    assertThat(product.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(product.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
  }

  void verifyProductSmallBox(Product product) {
    assertThat(product.getId()).isEqualTo(TestData.SMALL_BOX_PRODUCT_ID);
    assertThat(product.getCategory()).isEqualTo("Boxes");
    assertThat(product.getName()).isEqualTo("Box, Small");
    assertThat(product.getDescription()).isEqualTo("A nice small box");
    assertThat(product.getPrice()).isEqualTo(299);
    assertThat(product.getInventory()).isEqualTo(34);
    assertThat(product.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(product.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
  }

  void verifyProductBigBox(Product product) {
    assertThat(product.getId()).isEqualTo(TestData.BIG_BOX_PRODUCT_ID);
    assertThat(product.getCategory()).isEqualTo("Boxes");
    assertThat(product.getName()).isEqualTo("Box, Big");
    assertThat(product.getDescription()).isEqualTo("An okay big box");
    assertThat(product.getPrice()).isEqualTo(399);
    assertThat(product.getInventory()).isEqualTo(47);
    assertThat(product.getCreated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    assertThat(product.getUpdated()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
  }

  void delay() {
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
