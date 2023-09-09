package com.cydeo.service.impl;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {
    @Override
    public Account createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId) {
        //we need to create Account Object
        Account account = Account.builder().id(UUID.randomUUID()).userId(userId) //we did not get it .. created statically
        .balance(balance).accountType(accountType).creationDate(createDate).build(); //like an AllArgsConstructor

        //Why we do it ourself instead of Spring? :
        // if we are carrying and there is no ACTION, just store the data = > we create.
        // if there is ACTION we use @Component


        //Keep this object information at  somewhere


        //Save into database(repository)


        //return the object created


        return null;
    }

    @Override
    public List<Account> listAllAccount() {
        return null;
    }
}