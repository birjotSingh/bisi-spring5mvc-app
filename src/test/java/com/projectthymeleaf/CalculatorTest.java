package com.projectthymeleaf;

import com.projectthymeleaf.model.Expense;
import com.projectthymeleaf.model.TransactionType;
import com.projectthymeleaf.repository.ExpenseRepository;
import com.projectthymeleaf.service.Calculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CalculatorTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    Calculator calculator;


    Expense expense = Expense.builder().transactionType(TransactionType.CREDIT).amount(30.0).build();
    Expense expense1 = Expense.builder().transactionType(TransactionType.DEBIT).amount(-20.0).build();

    @Test
    public void testToCalIncome(){
        expenseRepository.insertExpense(expense);
        expenseRepository.insertExpense(expense1);
       Assertions.assertThat(calculator.incomeCal()).isEqualTo(30.0);
    }

    @Test
    public void testToCalExpense(){
        expenseRepository.insertExpense(expense);
        expenseRepository.insertExpense(expense1);
        Assertions.assertThat(calculator.expenseCal()).isEqualTo(-20.0);
    }

    @Test
    public void testToCalBalance(){
        expenseRepository.insertExpense(expense);
        expenseRepository.insertExpense(expense1);
        Assertions.assertThat(calculator.sumTotal()).isEqualTo(10.0);
    }

    @Test
    public void testSelectedTransactionType(){
        Expense expenseReturned = calculator.selectTransactionType(expense1);
        Assertions.assertThat(expenseReturned.getTransactionType()).isEqualTo(TransactionType.DEBIT);
    }
}
