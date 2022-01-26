package com.projectthymeleaf.repository;

import com.projectthymeleaf.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ExpenseRepositoryImp implements ExpenseRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    @Override
    public Expense save(Expense expense) {
        String query = "insert into expense (name, amount,date_creation) values (?,?,?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, expense.getName());
                preparedStatement.setInt(2, expense.getAmount());
                preparedStatement.setString(3, expense.getCreationDate());
                return preparedStatement;
            }
        }, keyHolder);

        expense.setId(keyHolder.getKey().intValue());

        return expense;
    }

    @Override
    public Expense getExpenseById(int id) {
        String query = "select * from expense where id= ?";
        Expense expense = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Expense.class));
        return expense;
    }

    @Override
    public List<Expense> findAllExpenses() {
        String query = "select * from expense";
        List<Expense> expenseList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(query);

        for (Map<String, Object> expenseRow : maps) {
            Expense expense = new Expense();
            expense.setId(Integer.valueOf(String.valueOf(expenseRow.get("id"))));
            expense.setAmount(Integer.valueOf(String.valueOf(expenseRow.get("amount"))));
            expense.setName(String.valueOf(expenseRow.get("name")));
            expenseList.add(expense);
        }
        return expenseList;
    }

    @Override
    public void deleteById(Integer id) {
        String query = "delete from expense where id =?";
        jdbcTemplate.update(query, id);
        System.out.println("deleted row!");
    }

    @Override
    public void update(Expense expense) {
        String query = "update expense set name=?, amount=?, date_creation=?, date_edited=? where id=?";
        Object[] args = new Object[]{expense.getName(), expense.getAmount(), expense.getCreationDate(), expense.getEditedDate(), expense.getId()};
        jdbcTemplate.update(query, args);
    }


}
