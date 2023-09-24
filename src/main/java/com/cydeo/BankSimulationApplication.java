package com.cydeo;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationApplication {

    public static void main(String[] args) {
        ApplicationContext container = SpringApplication.run(BankSimulationApplication.class, args);
//
//        //get account and transaction service beans
//        AccountService accountService = container.getBean(AccountService.class);
//        TransactionService transactionService = container.getBean(TransactionService.class);
//
//        //create 2 accounts sender and receiver
//        Account sender1 = accountService.createNewAccount(BigDecimal.valueOf(70), new Date(), AccountType.CHECKING, 1L);
//        Account receiver1 = accountService.createNewAccount(BigDecimal.valueOf(50), new Date(), AccountType.SAVING, 2L);
//        Account receiver2 = null;
//
//        accountService.listAllAccount().forEach(System.out::println);
//
//        //Happy path : both accounts CHECKING, balance is OK
//       transactionService.makeTransfer(sender1, receiver2, new BigDecimal(40), new Date(), "Transaction 1");
//        System.out.println(transactionService.findAllTransaction().get(0));
//        accountService.listAllAccount().forEach(System.out::println);

        //Negative Scenario1 : both accounts CHECKING, balance is not sufficient
//        Exception in thread "main" com.cydeo.exception.BalanceNotSufficientException: Balance is not enough for this transfer
//        at com.cydeo.service.impl.TransactionServiceImpl.executeBalanceAndUpdateIfRequired(TransactionServiceImpl.java:72)
//        at com.cydeo.service.impl.TransactionServiceImpl.makeTransfer(TransactionServiceImpl.java:51)
//        at com.cydeo.BankSimulationApplication.main(BankSimulationApplication.java:37)

//        //Negative Scenario2 : one account CHECKING receiver is SAVING
//        Exception in thread "main" com.cydeo.exception.AccountOwnershipException: If one of the accounts is saving, user must be the same for sender and receiver
//        at com.cydeo.service.impl.TransactionServiceImpl.checkAccountOwnership(TransactionServiceImpl.java:88)
//        at com.cydeo.service.impl.TransactionServiceImpl.makeTransfer(TransactionServiceImpl.java:50)
//        at com.cydeo.BankSimulationApplication.main(BankSimulationApplication.java:33)

        //Negative Scenario2 : one account CHECKING receiver is SAVING
//        Exception in thread "main" com.cydeo.exception.BadRequestException: Sender or Receiver can not be the same
//        at com.cydeo.service.impl.TransactionServiceImpl.validateAccount(TransactionServiceImpl.java:100)
//        at com.cydeo.service.impl.TransactionServiceImpl.makeTransfer(TransactionServiceImpl.java:49)
//        at com.cydeo.BankSimulationApplication.main(BankSimulationApplication.java:33)

        //Negative Scenario2 : one accounts CHECKING receiver is null
//        Exception in thread "main" com.cydeo.exception.BadRequestException: Sender or Receiver can not be null
//        at com.cydeo.service.impl.TransactionServiceImpl.validateAccount(TransactionServiceImpl.java:95)
//        at com.cydeo.service.impl.TransactionServiceImpl.makeTransfer(TransactionServiceImpl.java:49)
//        at com.cydeo.BankSimulationApplication.main(BankSimulationApplication.java:33)

    }

}
