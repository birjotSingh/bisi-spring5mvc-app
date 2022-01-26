package com.projectthymeleaf.repository;

import com.projectthymeleaf.model.Expense;

import java.util.List;

public interface ExpenseRepository{

    public Expense save(Expense expense);
    public Expense getExpenseById(int id);
    public List<Expense> findAllExpenses();
    public void deleteById(Integer id);

}
