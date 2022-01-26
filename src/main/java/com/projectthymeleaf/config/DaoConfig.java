package com.projectthymeleaf.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DaoConfig {

    @Value("${spring.datasource.ur}")
    String url;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.username}")
    String user;

    @Value("${spring.datasource.driver-class-name}")
    String driverClass;

    public DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }

}
