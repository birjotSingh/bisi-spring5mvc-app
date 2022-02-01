package com.projectthymeleaf.dto;

import com.projectthymeleaf.model.TransactionType;
import lombok.Data;

@Data
public class Expense {

    Integer id;
    String name;
    Double amount;
    String cdate;
    String dateEdited;
    TransactionType transactionType;

}
