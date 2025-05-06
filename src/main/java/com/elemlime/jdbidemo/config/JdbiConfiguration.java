package com.elemlime.jdbidemo.config;

import com.elemlime.jdbidemo.dao.CategoryDao;
import com.elemlime.jdbidemo.dao.CustomerDao;
import com.elemlime.jdbidemo.dao.OrderDao;
import com.elemlime.jdbidemo.dao.ProductDao;
import java.util.List;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JdbiConfiguration {
    private final static Logger log = LoggerFactory.getLogger(JdbiConfiguration.class);

    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> plugins, List<RowMapper<?>> mappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new PostgresPlugin());

        log.info("Installing Plugins... ({} found)", plugins.size());
        plugins.forEach(jdbi::installPlugin);

        log.info("Installing Mappers... ({} found)", mappers.size());
        mappers.forEach(jdbi::registerRowMapper);
        return jdbi;
    }
    
    @Bean
    public CategoryDao categoryDao(Jdbi jdbi) {
        return jdbi.onDemand(CategoryDao.class);
    }
    
    @Bean
    public ProductDao productDao(Jdbi jdbi) {
        return jdbi.onDemand(ProductDao.class);
    }
    
    @Bean
    public CustomerDao customerDao(Jdbi jdbi) {
        return jdbi.onDemand(CustomerDao.class);
    }
    
    @Bean
    public OrderDao orderDao(Jdbi jdbi) {
        return jdbi.onDemand(OrderDao.class);
    }
}
