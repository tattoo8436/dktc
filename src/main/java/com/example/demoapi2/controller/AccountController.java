package com.example.demoapi2.controller;

import com.example.demoapi2.dto.AccountResponseDTO;
import com.example.demoapi2.dto.AccountLoginDTO;
import com.example.demoapi2.exception.ApiInputException;
import com.example.demoapi2.service.iAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private iAccountService accountService;
    private MessageSource messageSource;

    private boolean checkAccount(String username, String password) {
        if (username == null || password == null) {
            return false;
        } else if (accountService.loginByAccount(username, password) == null) {
            return false;
        }
        return true;
    }

    @PostMapping("/login")
    public AccountResponseDTO loginByAccount(@RequestBody AccountLoginDTO accountLogin) {
        if (checkAccount(accountLogin.getUsername(), accountLogin.getPassword())) {
            return accountService.loginByAccount(accountLogin.getUsername(), accountLogin.getPassword());
        } else {
            throw new ApiInputException("Sai tài khoản");
        }
    }
}
