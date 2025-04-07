package com.elemlime.jdbidemo.test.dao;

import com.elemlime.jdbidemo.dao.CategoryDao;
import com.elemlime.jdbidemo.dao.CustomerDao;
import java.util.List;
import java.util.UUID;

public class TestData {
  public static final UUID BOXES_CATEGORY_ID = UUID.randomUUID();
  public static final UUID FRUITS_CATEGORY_ID = UUID.randomUUID();

  public static final UUID JDOE_CUSTOMER_ID = UUID.randomUUID();
  public static final UUID MSMITH_CUSTOMER_ID = UUID.randomUUID();

  public static final UUID ORDER_1_ID = UUID.randomUUID();
  public static final UUID ORDER_2_ID = UUID.randomUUID();

  public static final UUID APPLE_PRODUCT_ID = UUID.randomUUID();
  public static final UUID PEAR_PRODUCT_ID = UUID.randomUUID();
  public static final UUID SMALL_BOX_PRODUCT_ID = UUID.randomUUID();
  public static final UUID BIG_BOX_PRODUCT_ID = UUID.randomUUID();

  public static void loadCategories(CategoryTestDao categoryTestDao) {
    categoryTestDao.batchInsert(List.of(BOXES_CATEGORY_ID, FRUITS_CATEGORY_ID), List.of("Boxes", "Fruits"));
  }

  public static void loadCustomers(CustomerTestDao customerTestDao) {
    customerTestDao.batchInsert(List.of(JDOE_CUSTOMER_ID, MSMITH_CUSTOMER_ID),
        List.of("jdoe@example.com", "msmith@example.com"),
        List.of("John", "Mary"),
        List.of("Doe", "Smith"));
  }
}
