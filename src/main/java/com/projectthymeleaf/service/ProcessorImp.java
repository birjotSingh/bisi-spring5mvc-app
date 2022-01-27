package com.projectthymeleaf.service;

import com.projectthymeleaf.model.Expense;
import com.projectthymeleaf.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessorImp implements Processor {

    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public Expense save(Expense expense) {
        return expenseRepository.insertExpense(expense);
    }

    @Override
    public void remove(Integer id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public void updateExpense(Expense expense) {
        expenseRepository.update(expense);
    }

    @Override
    public Expense findExpenseById(Integer id) {
        return expenseRepository.getExpenseById(id);
    }

    @Override
    public List<Expense> findAllExpenses() {
        return expenseRepository.getAllExpenses();
    }
}
