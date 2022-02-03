package com.projectthymeleaf.service;

import com.projectthymeleaf.dto.Expense;
import com.projectthymeleaf.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessorImp implements Processor {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    Calculator calculator;


    @Override
    public Expense save(Expense expense) {
        expense.setCdate(calculator.currentDate());
        calculator.selectTransactionType(expense);
        return expenseRepository.insertExpense(expense);
    }

    @Override
    public void remove(Integer id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        expense.setDateEdited(calculator.currentDate());
        calculator.selectTransactionType(expense);
        return expenseRepository.update(expense);
    }

    @Override
    public Expense findExpenseById(Integer id) {
        Expense expensew1 = expenseRepository.getExpenseById(id);
        return expensew1;
    }

    @Override
    public List<Expense> findAllExpenses() {
        return expenseRepository.getAllExpenses();
    }
}
