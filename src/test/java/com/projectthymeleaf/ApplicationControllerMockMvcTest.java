package com.projectthymeleaf;

import com.projectthymeleaf.model.Expense;
import com.projectthymeleaf.dto.ExpenseDto;
import com.projectthymeleaf.service.ProcessorImp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ApplicationControllerMockMvcTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProcessorImp processorImp;

    @SneakyThrows
    @Test
    void testViewByID() {

        ExpenseDto expenseDto = ExpenseDto.builder().name("grocerry").amount(23.0).build();

        Expense expense = Expense.builder().name("grocerry").amount(23.0).build();
        BDDMockito
                .given(processorImp.findExpenseById(1))
                .willReturn(expense);

        mockMvc.perform(MockMvcRequestBuilders.get("/index/entry"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("transaction", expenseDto))
                .andExpect(view().name("view"));
    }

    @SneakyThrows
    @Test
    void testInsert() {

        ExpenseDto expenseDto = ExpenseDto.builder().name("grocerry").amount(23.0).build();

        Expense expense = Expense.builder().name("grocerry").amount(23.0).build();
        BDDMockito
                .given(processorImp.findExpenseById(1))
                .willReturn(expense);

        mockMvc.perform(MockMvcRequestBuilders.get("/index/view/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("transaction", expenseDto))
                .andExpect(view().name("view"));
    }
}
