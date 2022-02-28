package com.example.card.credit.service.impl;

import com.example.card.credit.entity.CreditCardEntity;
import com.example.card.credit.model.CreditCardRequest;
import com.example.card.credit.repository.CardDetailsRepository;
import com.example.card.credit.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @Override
    public void addCreditCard(CreditCardRequest creditCardRequest) {
        //Set default balance as ZERO if no input.
        Optional<BigDecimal> balance = Optional.ofNullable(creditCardRequest.getBalance());
        CreditCardEntity creditCardEntity = CreditCardEntity.builder().creditCardRequest(creditCardRequest).balance(balance.orElse(BigDecimal.ZERO)).build();
        cardDetailsRepository.save(creditCardEntity);
    }

    @Override
    public List<CreditCardRequest> getAllCreditCards() {
        List<CreditCardRequest> creditCardRequests = new ArrayList<>();
        creditCardRequests = cardDetailsRepository.findAll().stream().map(creditCard -> new CreditCardRequest(creditCard)).collect(Collectors.toList());
        return cardDetailsRepository.findAll().stream().map(creditCard -> new CreditCardRequest(creditCard)).collect(Collectors.toList());
    }
}
