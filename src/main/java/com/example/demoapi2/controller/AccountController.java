package com.example.demoapi2.controller;

import com.example.demoapi2.dto.AccountDTO;
import com.example.demoapi2.dto.AccountLoginDTO;
import com.example.demoapi2.exception.ApiInputException;
import com.example.demoapi2.model.Account;
import com.example.demoapi2.service.iAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private iAccountService accountService;
    private MessageSource messageSource;

    public boolean checkAccount(String username, String password) {
        if (accountService.getAccount(username, password) == null) {
            return false;
        }
        return true;
    }

    @PostMapping("/login")
    public AccountLoginDTO loginByAccount(@RequestBody Account account) {
        AccountLoginDTO accountLogin = new AccountLoginDTO();
        accountLogin.setUsername(account.getUsername());
        accountLogin.setPassword(account.getPassword());
        if (checkAccount(account.getUsername(), account.getPassword())) {
            accountLogin.setStatus(true);

        } else {
            //accountLogin.setStatus(false);
            throw new ApiInputException("Sai tài khoản");
        }
        return accountLogin;
    }

    @PostMapping("/list")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/account")
    public AccountDTO getAccountById(@RequestBody Account account) {
        AccountDTO accountDTO = accountService.getAccountById(account.getId());
        System.out.println(accountDTO);
        return accountDTO;
    }
}
