package com.example.projectbank.exceptions;

public class BankAccountNotFound extends Exception {
    public BankAccountNotFound(String bankAccountNotFound) {
        super(bankAccountNotFound);
    }
}
