package com.projectthymeleaf.dto;

import com.projectthymeleaf.model.ExpenseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseMapper {

    ExpenseMapper INSTANCE= Mappers.getMapper(ExpenseMapper.class);

    @Mapping(source="id", target="id")
    @Mapping(source="name", target="name")
    @Mapping(source="cdate", target="cdate")
    @Mapping(source="amount", target="amount")
    @Mapping(source="dateEdited", target="dateEdited")
    @Mapping(source="transactionType", target="transactionType")
    ExpenseDto toExpenseDto(Expense expense);


    @Mapping(source="id", target="id")
    @Mapping(source="name", target="name")
    @Mapping(source="cdate", target="cdate")
    @Mapping(source="amount", target="amount")
    @Mapping(source="dateEdited", target="dateEdited")
    @Mapping(source="transactionType", target="transactionType")
    Expense toExpense(ExpenseDto expenseDto);

}
