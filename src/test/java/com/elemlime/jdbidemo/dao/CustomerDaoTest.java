package com.elemlime.jdbidemo.dao;

import static org.assertj.core.api.Assertions.assertThat;

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
public class CustomerDaoTest extends AbstractDaoTest {
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
    var customers = customerDao.getAll();
    assertThat(customers).isEmpty();
  }

  @Test
  void testAll_WithData() {
    testData.loadCustomers();
    var customers = customerDao.getAll();
    assertThat(customers).hasSize(2);
    verifyCustomerJDoe(customers.get(0));
    verifyCustomerMSmith(customers.get(1));
  }

  @Test
  void testGetCustomerById_Empty() {
    var customerOpt = customerDao.getCustomerById(TestData.JDOE_CUSTOMER_ID);
    assertThat(customerOpt).isEmpty();
  }

  @Test
  void testGetCustomerById_WithData() {
    testData.loadCustomers();
    var customerOpt = customerDao.getCustomerById(TestData.MSMITH_CUSTOMER_ID);
    assertThat(customerOpt).isPresent();
    verifyCustomerMSmith(customerOpt.get());
  }

  @Test
  void testUpdateCustomer_Success() {
    testData.loadCustomers();
    delay();
    var customer =
        customerDao.updateCustomer(
            TestData.JDOE_CUSTOMER_ID, "jdover@example.com", "James", "Dover");
    assertThat(customer.getId()).isEqualTo(TestData.JDOE_CUSTOMER_ID);
    assertThat(customer.getEmail()).isEqualTo("jdover@example.com");
    assertThat(customer.getFirstName()).isEqualTo("James");
    assertThat(customer.getLastName()).isEqualTo("Dover");
    assertThat(customer.getUpdated().toEpochMilli()).isGreaterThan(customer.getCreated().toEpochMilli());
  }

  @Test
  void testCreateCustomer_Success() {
    var customer = customerDao.createCustomer("jdover@example.com", "James", "Dover");
    assertThat(customer.getFirstName()).isEqualTo("James");
    assertThat(customer.getLastName()).isEqualTo("Dover");
    assertThat(customer.getEmail()).isEqualTo("jdover@example.com");
  }

  @Test
  void testDeleteCustomer_Success() {
    testData.loadCustomers();
    customerDao.deleteCustomer(TestData.JDOE_CUSTOMER_ID);
    var customer = customerDao.getCustomerById(TestData.JDOE_CUSTOMER_ID);
    assertThat(customer).isEmpty();
  }
}
