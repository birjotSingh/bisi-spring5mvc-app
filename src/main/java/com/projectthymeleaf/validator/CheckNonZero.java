package com.projectthymeleaf.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class CheckNonZero implements ConstraintValidator<NotZero, Double> {

    @Override
    public void initialize(NotZero constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value !=null && value!=0;
    }

}
