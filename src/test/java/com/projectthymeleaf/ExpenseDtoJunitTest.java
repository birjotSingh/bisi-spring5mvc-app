package com.projectthymeleaf;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;

public class ExpenseDtoJunitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    public void testWithInvalidDescription() {
       /* ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setName("gas for w23car");
        System.out.println(expenseDto.getName());*/
        /*Assertions.assertTrue(expenseDto.getName().equals(null));*/

       /* Set<ConstraintViolation<ExpenseDto>> violations = validator.validate(expenseDto);
        Assertions.assertTrue(violations.size()==1);*/
    }


}
