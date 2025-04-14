package com.elemlime.jdbidemo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.elemlime.jdbidemo.TestDaoConfig;
import com.elemlime.jdbidemo.TestcontainersConfiguration;
import com.elemlime.jdbidemo.test.dao.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TestcontainersConfiguration.class, TestDaoConfig.class})
@SpringBootTest
public class CustomerDaoTest {

  @Autowired
  private CustomerDao customerDao;
  @Autowired
  private TestData testData;

  @BeforeEach
  void setUp() {
    testData.deleteAll();
  }

  @Test
  void testAll_Empty() {
    var result = customerDao.getAll();
    assertTrue(result.isEmpty());
  }

  @Test
  void testAll_WithData() {
    testData.loadCustomers();
    var result = customerDao.getAll();
    assertEquals(2, result.size());
    assertEquals(TestData.JDOE_CUSTOMER_ID, result.get(0).getId());
    assertEquals(TestData.MSMITH_CUSTOMER_ID, result.get(1).getId());
    assertEquals("John", result.get(0).getFirstName());
    assertEquals("Mary", result.get(1).getFirstName());
    assertEquals("Doe", result.get(0).getLastName());
    assertEquals("Smith", result.get(1).getLastName());
    assertEquals("jdoe@example.com", result.get(0).getEmail());
    assertEquals("msmith@example.com", result.get(1).getEmail());
  }

  @Test
  void testGetCustomerById_Empty() {
    var result = customerDao.getCustomerById(TestData.JDOE_CUSTOMER_ID);
    assertTrue(result.isEmpty());
  }

  @Test
  void testGetCustomerById_WithData() {
    testData.loadCustomers();
    var result = customerDao.getCustomerById(TestData.MSMITH_CUSTOMER_ID);
    assertTrue(result.isPresent());
    assertEquals(TestData.MSMITH_CUSTOMER_ID, result.get().getId());
    assertEquals("Mary", result.get().getFirstName());
    assertEquals("Smith", result.get().getLastName());
    assertEquals("msmith@example.com", result.get().getEmail());
  }

  @Test
  void testUpdateCustomer_Success() {
    testData.loadCustomers();
    var result =
        customerDao.updateCustomer(
            TestData.JDOE_CUSTOMER_ID, "jdover@example.com", "James", "Dover");
    assertEquals(TestData.JDOE_CUSTOMER_ID, result.getId());
    assertEquals("James", result.getFirstName());
    assertEquals("Dover", result.getLastName());
    assertEquals("jdover@example.com", result.getEmail());
    assertTrue(result.getUpdated().toEpochMilli() > result.getCreated().toEpochMilli());
  }

  @Test
  void testCreateCustomer_Success() {
    var result = customerDao.createCustomer("jdover@example.com", "James", "Dover");
    assertEquals("James", result.getFirstName());
    assertEquals("Dover", result.getLastName());
    assertEquals("jdover@example.com", result.getEmail());
  }

  @Test
  void testDeleteCustomer_Success() {
    testData.loadCustomers();
    customerDao.deleteCustomer(TestData.JDOE_CUSTOMER_ID);
    var result = customerDao.getCustomerById(TestData.JDOE_CUSTOMER_ID);
    assertTrue(result.isEmpty());
  }
}
