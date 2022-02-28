package com.example.card.credit.service;

import com.example.card.credit.model.CreditCardRequest;

import java.util.List;

public interface CreditCardService {
    public void addCreditCard(CreditCardRequest creditCardRequest);

    public List<CreditCardRequest> getAllCreditCards();
}
