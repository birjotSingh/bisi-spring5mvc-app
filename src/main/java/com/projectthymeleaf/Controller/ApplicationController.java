package com.projectthymeleaf.Controller;

import com.projectthymeleaf.model.Finances;
import com.projectthymeleaf.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController{

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/index")
    public String mainPage(Model model){
        model.addAttribute("finances", new Finances());
        return "index";
    }

    /*@PostMapping("/index/entry")
    public voi*/



}
