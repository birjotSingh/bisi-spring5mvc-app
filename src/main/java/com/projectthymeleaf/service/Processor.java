package com.projectthymeleaf.service;

import com.projectthymeleaf.model.Expense;

import java.util.List;

public interface Processor {

    Expense save(Expense expense);

    void remove(Integer id);

    Expense updateExpense(Expense expense);

    Expense findExpenseById(Integer id);

    List<Expense> findAllExpenses();

}
