package com.vijayrawatsan.jpahibernate.config;

import com.p6spy.engine.spy.P6DataSource;
import com.zaxxer.hikari.HikariDataSource;
import javax.persistence.ValidationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by vijayrawatsan on 01/08/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {""}, entityManagerFactoryRef = "entityManager")
@PropertySource("classpath:${spring.profiles.active}/mysql.properties")
public class MySqlConfig {

    private static final String[] PACKAGES_TO_SCAN = {"com.vijayrawatsan.jpahibernate"};

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManager() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory =
            new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(PACKAGES_TO_SCAN);
        factory.setDataSource(dataSource());
        factory.setPersistenceUnitName("jpahibernate");
        factory.setValidationMode(ValidationMode.NONE);
        return factory;
    }

    /**
     * HikariDataSource setup
     */
    @Bean
    public P6DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        hikariDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        hikariDataSource.setUsername(env.getProperty("jdbc.username"));
        hikariDataSource.setPassword(env.getProperty("jdbc.password"));
        hikariDataSource.setMaximumPoolSize(env.getProperty("jdbc.max.connections", Integer.class));
        hikariDataSource.setMinimumIdle(20);
        hikariDataSource.setMaxLifetime(7 * 60 * 1000);
        return new P6DataSource(hikariDataSource);
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManager().getObject());
        return txManager;
    }
}
