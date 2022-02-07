package com.projectthymeleaf.dto;

import com.projectthymeleaf.model.TransactionType;
import com.projectthymeleaf.validator.NotZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ExpenseDto {

    Integer id;

    @NotBlank(message = "name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Description should contain only alphabets and spaces!")
    String name;

    @NotNull(message = "amount cannot be null")
    @NotZero(message = "amount cannot be zero!")
    Double amount;

    String cdate;
    String dateEdited;
    TransactionType transactionType;

}
