package com.example.card.credit.unit.service;


import com.example.card.credit.entity.CreditCardEntity;
import com.example.card.credit.repository.CardDetailsRepository;
import com.example.card.credit.service.impl.CreditCardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceTest {

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Mock
    private CardDetailsRepository cardDetailsRepository;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCreditCardTest(){
        List<CreditCardEntity> creditCardEntities = Arrays.asList(CreditCardEntity.builder().cardNumber("5066-9911-1111-1118").name("Roger Singh").limit(BigDecimal.TEN).build(),
                CreditCardEntity.builder().cardNumber("5555555555554444").name("Jagjeet Dhillon").limit(BigDecimal.TEN).build());
        when(cardDetailsRepository.findAll()).thenReturn(creditCardEntities);
      assertEquals(creditCardService.getAllCreditCards().size(),2);
        assertEquals(creditCardService.getAllCreditCards().get(0).getLimit(),BigDecimal.TEN);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void getAllCreditCardTestDatabaseException(){
        when(cardDetailsRepository.findAll()).thenThrow(new DataIntegrityViolationException("Database exception"));
        creditCardService.getAllCreditCards();
    }
}
