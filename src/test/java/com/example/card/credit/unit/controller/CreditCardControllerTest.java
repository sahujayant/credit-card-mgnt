package com.example.card.credit.unit.controller;

import com.example.card.credit.model.CreditCardRequest;
import com.example.card.credit.rest.CreditCardController;
import com.example.card.credit.service.impl.CreditCardServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditCardController.class)
public class CreditCardControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreditCardServiceImpl creditCardService;

    @Test
    @DisplayName("Test to validate new addition of card.")
    public void testAddNewCreditCardApiSuccessful() throws Exception {
        //Create sample card.
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setGivenName("Rahul Yadav");
        creditCardRequest.setCardNumber("5555555555554444");
        creditCardRequest.setLimit(new BigDecimal(2550.0));
        creditCardRequest.setBalance(new BigDecimal(50.0));
        creditCardService.addCreditCard(Mockito.any(CreditCardRequest.class));
        mvc.perform(post("/creditCard")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(creditCardRequest).toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Test to fetch all  credit cards")
    public void testGetAllCreditCardApi() throws Exception {

        //Create sample card.
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setGivenName("Rahul Yadav");
        creditCardRequest.setCardNumber("5555555555554444");
        creditCardRequest.setLimit(new BigDecimal(1000.0));
        creditCardRequest.setBalance(new BigDecimal(50.0));

        //Mock getAllCredits service call
        Mockito.when(creditCardService.getAllCreditCards()).thenReturn(Arrays.asList((creditCardRequest)));
        mvc.perform(MockMvcRequestBuilders.get("/creditCard")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic ZGVtb3VzZXI6ZGVtb3Bhc3M="))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
