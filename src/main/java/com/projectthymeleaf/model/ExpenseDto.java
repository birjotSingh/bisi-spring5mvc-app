package com.projectthymeleaf.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ExpenseDto {

    Integer id;

    @NotBlank(message = "name cannot be blank")
    String name;

    @NotNull(message = "amount cannot be null")
    Double amount;

    String cdate;
    String dateEdited;
    TransactionType transactionType;

}
