package com.example.projectbank.mappers;

import com.example.projectbank.dtos.AccountOperationDTO;
import com.example.projectbank.dtos.ClientDTO;
import com.example.projectbank.dtos.CurrentBankAccountDTO;
import com.example.projectbank.dtos.SavingBankAccountDTO;
import com.example.projectbank.entities.AccountOperation;
import com.example.projectbank.entities.Client;
import com.example.projectbank.entities.CurrentAccount;
import com.example.projectbank.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingBankAccount) {
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingBankAccount, savingBankAccountDTO);
        savingBankAccountDTO.setClientDTO(fromClient(savingBankAccount.getClient()));
        savingBankAccountDTO.setType("SavingBankAccount");
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        savingAccount.setClient(fromClientDTO(savingBankAccountDTO.getClientDTO()));
        return savingAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setClientDTO(fromClient(currentAccount.getClient()));
        currentBankAccountDTO.setType("CurrentBankAccount");
        return currentBankAccountDTO;
    }

    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
        currentAccount.setClient(fromClientDTO(currentBankAccountDTO.getClientDTO()));
        return currentAccount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }


}
