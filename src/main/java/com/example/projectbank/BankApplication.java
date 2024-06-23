package com.example.projectbank;

import com.example.projectbank.dtos.BankAccountDTO;
import com.example.projectbank.dtos.ClientDTO;
import com.example.projectbank.dtos.CurrentBankAccountDTO;
import com.example.projectbank.dtos.SavingBankAccountDTO;
import com.example.projectbank.entities.*;
import com.example.projectbank.enums.AccountStatus;
import com.example.projectbank.exceptions.ClientNotFoundException;
import com.example.projectbank.repositories.AccountOperationRepository;
import com.example.projectbank.repositories.BankAccountRepository;
import com.example.projectbank.repositories.ClientRepository;
import com.example.projectbank.sevices.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan", "Youssef", "Boumya").forEach(name -> {
                ClientDTO client = new ClientDTO();
                client.setName(name);
                client.setEmail(name+"@yahoo.com");
                bankAccountService.saveClient(client);
            });

            bankAccountService.listClients().forEach(client -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 10000, 1000, client.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 10000, 4.5, client.getId());
                } catch (ClientNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts = bankAccountService.listBankAccounts();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i = 0; i < 10; i++) {
                    String accountID;
                    if (bankAccount instanceof SavingBankAccountDTO){
                        accountID = ((SavingBankAccountDTO) bankAccount).getId();
                    } else {
                        accountID = ((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountID, 1000 + Math.random()*100000, "Credit");
                    bankAccountService.debit(accountID, 1000 + Math.random()*1000, "Debit");
                }

            }
        };
    }
    //@Bean is // thats why not working
    CommandLineRunner start(ClientRepository clientRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("salma", "jamila", "karam").forEach(name -> {
                Client client = new Client();
                client.setName(name);
                client.setEmail(name + "@gmail.com");
                clientRepository.save(client);
            });
            clientRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 100000);
                currentAccount.setCreateAcc(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setClient(cust);
                currentAccount.setOverDraft(1000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 100000);
                savingAccount.setCreateAcc(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setClient(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
        };
    }

}
