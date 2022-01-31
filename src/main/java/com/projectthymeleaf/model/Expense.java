package com.projectthymeleaf.model;

import lombok.Data;

@Data
public class Expense {

    Integer id;
    String name;
    Integer amount;
    String cdate;
    String dateEdited;
    TransactionType type;

}
