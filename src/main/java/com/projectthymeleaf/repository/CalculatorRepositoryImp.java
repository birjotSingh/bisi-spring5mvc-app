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
        String query = "select sum(amount) from expense where transaction_type=?";
        return jdbcTemplate.queryForObject(query,new Object[]{"CREDIT"},Integer.class);
    }

    @Override
    public Integer expenseTotal() {
        String query = "select sum(amount) from expense where transaction_type=?";
        return jdbcTemplate.queryForObject(query,new Object[]{"DEBIT"},Integer.class);
    }
}
