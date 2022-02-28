package com.example.card.credit.model;


import com.example.card.credit.entity.CreditCardEntity;

import com.example.card.credit.validation.LuhnValidation;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


public class CreditCardRequest {

    @LuhnValidation
    @NotBlank
    @Size(max = 19)
    private String cardNumber;

    @NotBlank
    @Schema(required = true)
    private String givenName;

    @Schema(required = true)
    private BigDecimal limit;

    @Schema(required = false)
    private BigDecimal balance;

    public CreditCardRequest() {
    }

    public CreditCardRequest(String cardNumber, String givenName, BigDecimal limit, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.givenName = givenName;
        this.limit = limit;
        this.balance = balance;
    }

    public CreditCardRequest(CreditCardEntity creditCardEntity) {
        this.cardNumber = creditCardEntity.getCreditCardNumber();
        this.givenName = creditCardEntity.getGivenName();
        this.limit = creditCardEntity.getCreditLimit();
        this.balance = creditCardEntity.getBalance();
    }

    public CreditCardRequest(String cardNumber, String givenName, BigDecimal limit) {
        this.cardNumber = cardNumber;
        this.givenName = givenName;
        this.limit = limit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "CreditCardRequest{" +
                "cardNumber='" + cardNumber + '\'' +
                ", givenName='" + givenName + '\'' +
                ", limit=" + limit +
                ", balance=" + balance +
                '}';
    }
}