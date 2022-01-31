package com.projectthymeleaf.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CalculatorRepositoryImp implements CalculatorRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer total() {
        String query = "select sum(amount) from expense";
        return jdbcTemplate.queryForObject(
                query, Integer.class);

    }

    @Override
    public Integer incomeTotal() {
        return null;
    }

    @Override
    public Integer expenseTotal() {
        return null;
    }
}
