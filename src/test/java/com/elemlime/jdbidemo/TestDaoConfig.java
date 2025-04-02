package com.elemlime.jdbidemo;

import com.elemlime.jdbidemo.test.dao.CategoryTestDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestDaoConfig {
  @Bean
  public CategoryTestDao categoryTestDao(Jdbi jdbi) { return jdbi.onDemand(CategoryTestDao.class); }
}
