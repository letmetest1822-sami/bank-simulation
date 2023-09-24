package com.cydeo.controller;

import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //write a method to return index.html including account list information
//    @RequestMapping("/index")   //also we can use @GetMapping or we can define method POST or ...
    @GetMapping("index")
    public String getIndexPage(Model model){  //
        model.addAttribute("accountList", accountService.listAllAccounts());
        return "account/index";                //
    }
}
