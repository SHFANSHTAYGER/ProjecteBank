package com.example.projectbank.dtos;


import com.example.projectbank.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO{
    private String id;
    private double balance;
    private Date createAcc;
    private AccountStatus status;
    private ClientDTO clientDTO;
    private double overDraft;
}
