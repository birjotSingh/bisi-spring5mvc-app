package com.projectthymeleaf;

import com.projectthymeleaf.dto.Expense;
import com.projectthymeleaf.model.TransactionType;
import com.projectthymeleaf.repository.ExpenseRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    ExpenseRepository expenseRepository;

    Expense expense = Expense.builder().transactionType(TransactionType.CREDIT).amount(23.0).build();
    Expense expense1 = Expense.builder().transactionType(TransactionType.DEBIT).amount(-34.0).build();


    @Test
    public void testFindAllExpenses() {
        expenseRepository.insertExpense(expense);
        expenseRepository.insertExpense(expense1);
        Assertions.assertThat(expenseRepository.getAllExpenses().size()).isEqualTo(2);
    }

    @Test
    public void testInsertAndGetExpenseById() throws Exception {
        Expense expenseObj = expenseRepository.insertExpense(expense);
        Expense expenseGetById = expenseRepository.getExpenseById(expenseObj.getId());
        Assertions.assertThat(expenseObj).isEqualTo(expenseGetById);
    }


    @Test
    public void testUpdateExpense() {
        expenseRepository.insertExpense(expense);
        Expense e = expenseRepository.insertExpense(expense1);
        expenseRepository.update(e.toBuilder().amount(50.0).build());
        Assertions.assertThat(expenseRepository.getExpenseById(e.getId()).getAmount()).isEqualTo(50.0);
    }


    @Test
    public void testDeleteExpense() {
        Expense expenseObj = expenseRepository.insertExpense(expense);
        expenseRepository.deleteById(expenseObj.getId());
        Assertions.assertThat(expenseObj).isNotIn(expenseRepository.getAllExpenses());
    }

}
