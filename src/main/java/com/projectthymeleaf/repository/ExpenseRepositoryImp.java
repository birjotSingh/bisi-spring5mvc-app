package com.projectthymeleaf.repository;

import com.projectthymeleaf.model.Expense;
import com.projectthymeleaf.model.TransactionType;
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
import java.util.Optional;

@Repository
public class ExpenseRepositoryImp implements ExpenseRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    @Override
    public Expense insertExpense(Expense expense) {
        String query = "insert into expense (name, amount,transaction_type, cdate) values (?,?,?,?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, expense.getName());
                preparedStatement.setInt(2, expense.getAmount());
                preparedStatement.setString(3, expense.getType().name());
                preparedStatement.setString(4, expense.getCdate());
                return preparedStatement;
            }
        }, keyHolder);

        Optional<Number> key = Optional.ofNullable(keyHolder.getKey());
        key.ifPresent(number -> expense.setId(number.intValue()));

        return expense;
    }

    @Override
    public Expense getExpenseById(int id) {
        String query = "select * from expense where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Expense.class));
    }

    @Override
    public List<Expense> getAllExpenses() {
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
    }

    @Override
    public void update(Expense expense) {
        String query = "update expense set name=?, amount=?, transaction_type=?, dateEdited=? where id=?";
        Object[] args = new Object[]{expense.getName(), expense.getAmount(),expense.getType().name(), expense.getDateEdited(), expense.getId()};
        jdbcTemplate.update(query, args);
    }


}
