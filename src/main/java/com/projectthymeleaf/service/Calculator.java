package com.projectthymeleaf.service;

import com.projectthymeleaf.model.Expense;
import com.projectthymeleaf.model.TransactionType;
import com.projectthymeleaf.repository.CalculatorRepositoryImp;
import com.projectthymeleaf.repository.ExpenseRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class Calculator {

    @Autowired
    ExpenseRepositoryImp expenseRepositoryImp;

    @Autowired
    CalculatorRepositoryImp calculatorRepositoryImp;

    public Integer sumTotal() {
        return calculatorRepositoryImp.total();
    }

    public Integer incomeCal() {
       /* Integer in = 0;
        List<Expense> expenseList = expenseRepositoryImp.getAllExpenses();
        for (Expense expense : expenseList
        ) {
            if (expense.getAmount() > 0) {
                in = in + expense.getAmount();
            }
        }*/
        return calculatorRepositoryImp.incomeTotal();
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

   public Expense selectTransactionType(Expense expense) {
        if (expense.getAmount() >= 0) {
            expense.setType(TransactionType.CREDIT);
        } else {
            expense.setType(TransactionType.DEBIT);
        }
        return expense;
    }
}
