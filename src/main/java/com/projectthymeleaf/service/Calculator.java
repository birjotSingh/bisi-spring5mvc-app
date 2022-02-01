package com.projectthymeleaf.service;

import com.projectthymeleaf.dto.Expense;
import com.projectthymeleaf.model.ExpenseDto;
import com.projectthymeleaf.model.TransactionType;
import com.projectthymeleaf.repository.CalculatorRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Calculator {

    @Autowired
    CalculatorRepositoryImp calculatorRepositoryImp;

    public Double sumTotal() {
        Double total = calculatorRepositoryImp.total();
        if(total==(null)){
            return 0.0;
        }
        return total;
    }

    public Double incomeCal() {
        Double aDouble = calculatorRepositoryImp.incomeTotal();
        if(aDouble==(null)){
            return 0.0;
        }
        return aDouble;
    }

    public Double expenseCal() {
        Double aDouble = calculatorRepositoryImp.expenseTotal();
        if(aDouble==(null)){
            return 0.0;
        }
        return aDouble;
    }

    public String currentDate() {
        Date date = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        return ft.format(date);
    }

   public Expense selectTransactionType(Expense expense) {
        if (expense.getAmount() >= 0) {
            expense.setTransactionType(TransactionType.CREDIT);
        } else {
            expense.setTransactionType(TransactionType.DEBIT);
        }
        return expense;
    }
}