package com.reactivestax.spring5mvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("db.props")
public class DaoConfig {

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.username}")
    String user;

    @Bean
    public DataSource createDataSource() {

        HikariConfig dataSource = new HikariConfig();
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(user);
        return new HikariDataSource(dataSource);

    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(createDataSource());
        return jdbcTemplate;
    }


}

