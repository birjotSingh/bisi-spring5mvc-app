package com.projectthymeleaf.service;

import com.projectthymeleaf.model.Expense;
import com.projectthymeleaf.repository.ExpenseRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Calculator {

    @Autowired
    ExpenseRepositoryImp expenseRepositoryImp;

    public Integer sumTotal() {
        Integer sum = 0;
        List<Expense> expenseList = expenseRepositoryImp.getAllExpenses();
        for (Expense expense : expenseList
        ) {
            sum = sum + expense.getAmount();
        }
        return sum;
    }

    public Integer incomeCal() {
        Integer in = 0;
        List<Expense> expenseList = expenseRepositoryImp.getAllExpenses();
        for (Expense expense : expenseList
        ) {
            if (expense.getAmount() > 0) {
                in = in + expense.getAmount();
            }
        }
        return in;
    }

    public Integer expenseCal() {
        Integer out = 0;
        List<Expense> expenseList = expenseRepositoryImp.getAllExpenses();
        for (Expense expense : expenseList
        ) {
            if (expense.getAmount() < 0) {
                out = out + expense.getAmount();
            }
        }
        return out;
    }

    public String currentDate() {
        Date date = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        return ft.format(date);
    }
}
