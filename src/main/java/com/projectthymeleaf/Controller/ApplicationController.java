package com.projectthymeleaf.Controller;

import com.projectthymeleaf.model.Expense;
import com.projectthymeleaf.repository.ExpenseRepositoryImp;
import com.projectthymeleaf.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController {

    @Autowired
    ExpenseRepositoryImp repositoryImp;

    @Autowired
    Calculator calculator;

    @GetMapping("/index")
    public String mainPage(Model model) {
        model.addAttribute("finances", new Expense());
        model.addAttribute("expenses", repositoryImp.findAllExpenses());
        model.addAttribute("balance", calculator.sumTotal());
        model.addAttribute("in", calculator.incomeCal());
        model.addAttribute("out", calculator.expenseCal());
        return "index";
    }

    @PostMapping("/index/entry")
    public String newEntry(Expense expense, Model model) {
        if (expense.getId() == null) {
            expense.setCreationDate(calculator.currentDate());
            repositoryImp.save(expense);
        } else {
            expense.setEditedDate(calculator.currentDate());
            repositoryImp.update(expense);
        }
        return "redirect:/index";
    }


    @GetMapping("/index/view/{id}")
    public String viewEntry(@PathVariable Integer id, Model model) {
        model.addAttribute("transaction", repositoryImp.getExpenseById(id));
        return "view";
    }

    @GetMapping("/index/delete/{id}")
    public String deleteWidget(@PathVariable Integer id) {
        repositoryImp.deleteById(Math.toIntExact(id));
        return "redirect:/index";
    }

    @GetMapping("/index/edit/{id}")
    public String editTransaction(@PathVariable Integer id, Model model) {
        model.addAttribute("expenses", repositoryImp.findAllExpenses());
        model.addAttribute("balance", calculator.sumTotal());
        model.addAttribute("in", calculator.incomeCal());
        model.addAttribute("out", calculator.expenseCal());
        model.addAttribute("finances", repositoryImp.getExpenseById(id));
        return "index";
    }
}
