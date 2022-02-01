package com.projectthymeleaf.controller;

import com.projectthymeleaf.dto.ExpenseMapper;
import com.projectthymeleaf.model.ExpenseDto;
import com.projectthymeleaf.service.Calculator;
import com.projectthymeleaf.service.ProcessorImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class ApplicationController {

    @Autowired
    ProcessorImp processorImp;

    @Autowired
    Calculator calculator;

    @GetMapping("/index")
    public String mainPage(Model model) {
        model.addAttribute("finances", new ExpenseDto());
        model.addAttribute("expenses", processorImp.findAllExpenses().stream().map(ExpenseMapper.INSTANCE::toExpenseDto).collect(Collectors.toList()));
        model.addAttribute("balance", calculator.sumTotal());
        model.addAttribute("in", calculator.incomeCal());
        model.addAttribute("out", calculator.expenseCal());
        return "index";
    }

    @PostMapping("/index/entry")
    public String newEntry(@Valid ExpenseDto expenseDto, Model model) {
        if (expenseDto.getId() == null) {
            processorImp.save(ExpenseMapper.INSTANCE.toExpense(expenseDto));
        } else {
            processorImp.updateExpense(ExpenseMapper.INSTANCE.toExpense(expenseDto));
        }
        return "redirect:/index";
    }


    @GetMapping("/index/view/{id}")
    public String viewEntry(@PathVariable Integer id, Model model) {
        ExpenseDto expenseById = ExpenseMapper.INSTANCE.toExpenseDto(processorImp.findExpenseById(id));
        model.addAttribute("transaction", expenseById);
        return "view";
    }

    @GetMapping("/index/delete/{id}")
    public String deleteTransaction(@PathVariable Integer id) {
        processorImp.remove(id);
        return "redirect:/index";
    }

    @GetMapping("/index/edit/{id}")
    public String editTransaction(@PathVariable Integer id, Model model) {
        model.addAttribute("expenses", processorImp.findAllExpenses().stream().map(ExpenseMapper.INSTANCE::toExpenseDto).collect(Collectors.toList()));//
        model.addAttribute("balance", calculator.sumTotal());
        model.addAttribute("in", calculator.incomeCal());
        model.addAttribute("out", calculator.expenseCal());
        model.addAttribute("finances", ExpenseMapper.INSTANCE.toExpenseDto(processorImp.findExpenseById(id)));
        return "index";
    }
}
