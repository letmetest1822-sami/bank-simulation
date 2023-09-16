package com.cydeo.service.impl;

import com.cydeo.exception.BadRequestException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository; //Inject the repository --- Why Private Final? it will ask constructor

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /* Validation points....if it is NOT Happy Path =>  create many small methods for these conditions :Throw EXCEPTION and stop transfer
            - if sender or receiver is null
            - if sender or receiver is the same account
            - if sender has enough balance to make transfer
            - if both accounts are checking, if not, one of them is saving, it needs to be same userId
         */
        validateAccount(sender,receiver);
        //makeTransfer



        return null;
    }
    private void validateAccount(Account sender, Account receiver) {
        // -if sender or receiver is null; Throw EXCEPTION and stop transfer
        if(sender==null||receiver==null){
            throw new BadRequestException("Sender or Receiver can not be null");
        }

        // -if sender or receiver is the same; Throw EXCEPTION and stop transfer
        if(sender.getId()==receiver.getId()){
            throw new BadRequestException("Sender or Receiver can not be the same"); //we can even create a new exception
        }
        // -if sender or receiver exists in the repository; Throw EXCEPTION and stop transfer

        findAccountById(sender.getId());
        findAccountById(receiver.getId());
    }

    private void findAccountById(UUID id) {

        accountRepository.findById(id);


    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }

}
