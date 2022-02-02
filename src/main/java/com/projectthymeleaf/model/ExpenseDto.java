package com.projectthymeleaf.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ExpenseDto {

    Integer id;

    @NotBlank(message = "name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Description should contain only alphabets and spaces!")
    String name;

    @NotNull(message = "amount cannot be null")
    Double amount;

    String cdate;
    String dateEdited;
    TransactionType transactionType;

}
