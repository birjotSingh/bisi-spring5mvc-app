package com.projectthymeleaf.Controller;

import com.projectthymeleaf.model.Expense;
import com.projectthymeleaf.repository.ExpenseRepository;
import com.projectthymeleaf.repository.ExpenseRepositoryImp;
import com.projectthymeleaf.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController{

    @Autowired
    ExpenseRepositoryImp repositoryImp;

    @Autowired
    Calculator calculator;

    @GetMapping("/index")
    public String mainPage(Model model){
        model.addAttribute("finances", new Expense());
        model.addAttribute("expenses", repositoryImp.findAllExpenses());
        model.addAttribute("balance", calculator.sumTotal());
        model.addAttribute("in", calculator.incomeCal());
        model.addAttribute("out", calculator.expenseCal());
        return "index";
    }

    @PostMapping("/index/entry")
    public String newEntry(Expense expense, Model model){
        repositoryImp.save(expense);
        return "redirect:/index";
    }



}
