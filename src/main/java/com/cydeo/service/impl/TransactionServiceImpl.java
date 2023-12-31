package com.cydeo.service.impl;

import com.cydeo.enums.AccountType;

import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.exception.UnderConstructionException;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;

import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.TransactionRepository;

import com.cydeo.service.TransactionService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("false") //("${under_construction")
    private  boolean underConstruction;
    private final AccountRepository accountRepository; //Inject the repository --- Why Private Final? it will ask constructor
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /* Validation points....if it is NOT Happy Path =>  create many small methods for these conditions :Throw EXCEPTION and stop transfer
            - if sender or receiver is null
            - if sender or receiver is the same account
            - if sender has enough balance to make transfer
            - if both accounts are checking, if not, one of them is saving, it needs to be same userId
         */
        if (!underConstruction) {
            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);
      /*
            after all validations are completed, and money is transferred, we need to create Transaction object and save/return it.
         */
            Transaction transaction = Transaction.builder().amount(amount).sender(sender.getId()).receiver(receiver.getId())
                    .createDate(creationDate).message(message).build();
            //save into the db and return it
            return transactionRepository.save(transaction);
        }
        else {
            throw new UnderConstructionException("App is under construction, please try again later.");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver){
        if (checkSenderBalance(sender, amount)){
            //update sender and receiver balance
            //100 - 80
            sender.setBalance(sender.getBalance().subtract(amount));
            //50 + 80
            receiver.setBalance(receiver.getBalance().add(amount));
        }
        else {
            throw new BalanceNotSufficientException("Balance is not enough for this transfer");
        }
    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        //verify sender has enough balance to send
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;
    }

    private void checkAccountOwnership(Account sender, Account receiver) {
        /*
            write an if statement that checks if one of the account is saving,
            and user of sender or receiver is not the same, throw AccountOwnershipException
         */
        if((sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))
                && !sender.getUserId().equals(receiver.getUserId())){
            throw new AccountOwnershipException("If one of the accounts is saving, user must be the same for sender and receiver");
        }
    }

    private void validateAccount(Account sender, Account receiver) {
        // -if sender or receiver is null; Throw EXCEPTION and stop transfer
        if(sender==null || receiver==null){
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
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

}
