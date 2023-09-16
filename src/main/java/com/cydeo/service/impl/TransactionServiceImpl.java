package com.cydeo.service.impl;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
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
        // -if sender or receiver is the same; Throw EXCEPTION and stop transfer
        // -if sender or receiver exists in the repository; Throw EXCEPTION and stop transfer
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }

}
