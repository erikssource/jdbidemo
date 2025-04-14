package com.elemlime.jdbidemo.test.dao;

import com.elemlime.jdbidemo.model.OrderStatus;
import com.elemlime.jdbidemo.test.model.OrderLineTestDto;
import com.elemlime.jdbidemo.test.model.OrderTestDto;
import com.elemlime.jdbidemo.test.model.ProductTestDto;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TestData {
  public static final UUID BOXES_CATEGORY_ID = UUID.randomUUID();
  public static final UUID FRUITS_CATEGORY_ID = UUID.randomUUID();

  public static final UUID JDOE_CUSTOMER_ID = UUID.randomUUID();
  public static final UUID MSMITH_CUSTOMER_ID = UUID.randomUUID();

  public static final UUID ORDER_1_ID = UUID.randomUUID();
  public static final UUID ORDER_1_LINE_1_ID = UUID.randomUUID();
  public static final UUID ORDER_1_LINE_2_ID = UUID.randomUUID();
  public static final UUID ORDER_2_ID = UUID.randomUUID();

  public static final UUID APPLE_PRODUCT_ID = UUID.randomUUID();
  public static final UUID PEAR_PRODUCT_ID = UUID.randomUUID();
  public static final UUID SMALL_BOX_PRODUCT_ID = UUID.randomUUID();
  public static final UUID BIG_BOX_PRODUCT_ID = UUID.randomUUID();

  private final CustomerTestDao customerTestDao;
  private final CategoryTestDao categoryTestDao;
  private final ProductTestDao productTestDao;
  private final OrderTestDao orderTestDao;

  public TestData(CustomerTestDao customerTestDao, CategoryTestDao categoryTestDao, ProductTestDao productTestDao, OrderTestDao orderTestDao) {
    this.customerTestDao = customerTestDao;
    this.categoryTestDao = categoryTestDao;
    this.productTestDao = productTestDao;
    this.orderTestDao = orderTestDao;
  }

  public void deleteAll() {
    customerTestDao.deleteAll();
    categoryTestDao.deleteAll();
    productTestDao.deleteAll();
    orderTestDao.deleteAll();
  }


  public void loadCategories() {
    categoryTestDao.batchInsert(List.of(BOXES_CATEGORY_ID, FRUITS_CATEGORY_ID), List.of("Boxes", "Fruits"));
  }

  public void loadCustomers() {
    customerTestDao.batchInsert(List.of(JDOE_CUSTOMER_ID, MSMITH_CUSTOMER_ID),
        List.of("jdoe@example.com", "msmith@example.com"),
        List.of("John", "Mary"),
        List.of("Doe", "Smith"));
  }

  public void loadProducts() {
    productTestDao.batchInsert(
        new ProductTestDto(APPLE_PRODUCT_ID, FRUITS_CATEGORY_ID, "Apple", "A tasty red apple", 125, 50),
        new ProductTestDto(SMALL_BOX_PRODUCT_ID, BOXES_CATEGORY_ID, "Box, Small", "A nice small box", 299, 34),
        new ProductTestDto(PEAR_PRODUCT_ID, FRUITS_CATEGORY_ID, "Pear", "A mostly good pear", 119, 144),
        new ProductTestDto(BIG_BOX_PRODUCT_ID, BOXES_CATEGORY_ID, "Box, Big", "An okay big box", 399, 47)
    );
  }

  public void loadOrders() {
    loadCategories();
    loadCustomers();
    loadProducts();
    orderTestDao.insertOrders(
        List.of(
            new OrderTestDto(ORDER_1_ID, JDOE_CUSTOMER_ID, OrderStatus.COMPLETED),
            new OrderTestDto(ORDER_2_ID, MSMITH_CUSTOMER_ID, OrderStatus.CREATED)));
    orderTestDao.insertOrderLines(
        List.of(
            new OrderLineTestDto(ORDER_1_LINE_1_ID, ORDER_1_ID, APPLE_PRODUCT_ID, 6, 125),
            new OrderLineTestDto(ORDER_1_LINE_2_ID, ORDER_1_ID, SMALL_BOX_PRODUCT_ID, 2, 299)));
  }
}
