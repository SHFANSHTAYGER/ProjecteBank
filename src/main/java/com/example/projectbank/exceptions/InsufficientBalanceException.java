package com.example.projectbank.exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String insufficientFundsToConductTransaction) {
        super(insufficientFundsToConductTransaction);
    }
}
