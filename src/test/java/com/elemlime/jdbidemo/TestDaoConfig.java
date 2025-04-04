package com.elemlime.jdbidemo;

import com.elemlime.jdbidemo.test.dao.CategoryTestDao;
import com.elemlime.jdbidemo.test.dao.CustomerTestDao;
import com.elemlime.jdbidemo.test.dao.OrderTestDao;
import com.elemlime.jdbidemo.test.dao.ProductTestDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestDaoConfig {
  @Bean
  public CategoryTestDao categoryTestDao(Jdbi jdbi) { return jdbi.onDemand(CategoryTestDao.class); }

  @Bean
  public CustomerTestDao customerTestDao(Jdbi jdbi) { return jdbi.onDemand(CustomerTestDao.class); }

  @Bean
  public OrderTestDao orderTestDao(Jdbi jdbi) { return jdbi.onDemand(OrderTestDao.class); }

  @Bean
  public ProductTestDao productTestDao(Jdbi jdbi) { return jdbi.onDemand(ProductTestDao.class); }
}
