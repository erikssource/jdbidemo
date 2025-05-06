package com.elemlime.jdbidemo;

import com.elemlime.jdbidemo.test.dao.CategoryHelperDao;
import com.elemlime.jdbidemo.test.dao.CustomerHelperDao;
import com.elemlime.jdbidemo.test.dao.OrderHelperDao;
import com.elemlime.jdbidemo.test.dao.ProductHelperDao;
import com.elemlime.jdbidemo.test.dao.TestData;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestDaoConfig {
  @Bean
  public CategoryHelperDao categoryTestDao(Jdbi jdbi) {
    return jdbi.onDemand(CategoryHelperDao.class);
  }

  @Bean
  public CustomerHelperDao customerTestDao(Jdbi jdbi) {
    return jdbi.onDemand(CustomerHelperDao.class);
  }

  @Bean
  public OrderHelperDao orderTestDao(Jdbi jdbi) {
    return jdbi.onDemand(OrderHelperDao.class);
  }

  @Bean
  public ProductHelperDao productTestDao(Jdbi jdbi) {
    return jdbi.onDemand(ProductHelperDao.class);
  }

  @Bean
  public TestData testData(
      CustomerHelperDao customerHelperDao,
      CategoryHelperDao categoryHelperDao,
      ProductHelperDao productHelperDao,
      OrderHelperDao orderHelperDao) {
    return new TestData(customerHelperDao, categoryHelperDao, productHelperDao, orderHelperDao);
  }
}
