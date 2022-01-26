package com.projectthymeleaf.model;

import lombok.Data;

import java.util.Date;

@Data
public class Expense {

    Integer id;
    String name;
    Integer amount;
    String cdate;
    String dateEdited;

}
