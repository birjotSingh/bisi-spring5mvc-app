package com.projectthymeleaf.repository;

import com.projectthymeleaf.model.Expense;

import java.util.List;

public interface ExpenseRepository {

     Expense insertExpense(Expense expense);

     Expense getExpenseById(int id);

     List<Expense> getAllExpenses();

     void deleteById(Integer id);

     void update(Expense expense);

}
