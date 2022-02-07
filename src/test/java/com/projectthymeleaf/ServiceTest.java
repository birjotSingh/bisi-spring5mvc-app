package com.projectthymeleaf;

import com.projectthymeleaf.dto.Expense;
import com.projectthymeleaf.model.TransactionType;
import com.projectthymeleaf.repository.ExpenseRepository;
import com.projectthymeleaf.service.Processor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ServiceTest {

    @Autowired
    Processor processor;

    @MockBean
    ExpenseRepository expenseRepository;

    Expense expense1 = Expense.builder().transactionType(TransactionType.CREDIT).amount(23.0).build();
    Expense expense2 = Expense.builder().transactionType(TransactionType.DEBIT).amount(-34.0).build();

    @Test
    public void saveTest() {
        BDDMockito.given(expenseRepository.insertExpense(expense1)).willReturn(expense1);
        Expense expense = processor.save(expense1);
        Assertions.assertThat(expense).isEqualTo(expense1);
    }

    @Test
    public void updateTest(){
        BDDMockito.given(expenseRepository.update(expense1)).willReturn(expense1);
        Expense expense = processor.updateExpense(expense1);
        Assertions.assertThat(expense).isEqualTo(expense1);
    }

    @Test
    public void findExpenseByIdTest(){
        BDDMockito.given(expenseRepository.getExpenseById(1)).willReturn(expense1);
        Expense expense = processor.findExpenseById(1);
        Assertions.assertThat(expense).isEqualTo(expense1);
    }

    @Test
    public void findAll(){
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(expense1);
        expenseList.add(expense2);
        BDDMockito.given(expenseRepository.getAllExpenses()).willReturn(expenseList);
        List<Expense> list = processor.findAllExpenses();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }


}
