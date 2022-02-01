package com.projectthymeleaf.repository;

import com.projectthymeleaf.dto.Expense;
import com.projectthymeleaf.model.ExpenseDto;

import java.util.List;

public interface ExpenseRepository {

     Expense insertExpense(Expense expense);

     Expense getExpenseById(int id);

     List<Expense> getAllExpenses();

     void deleteById(Integer id);

     Expense update(Expense expense);

}
