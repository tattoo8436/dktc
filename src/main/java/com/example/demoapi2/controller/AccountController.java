package com.example.demoapi2.controller;

import com.example.demoapi2.dto.AccountResponseDTO;
import com.example.demoapi2.dto.AccountLoginDTO;
import com.example.demoapi2.exception.ApiInputException;
import com.example.demoapi2.service.AccountService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Getter
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    private boolean checkAccount(AccountLoginDTO accountLogin) {
        if (accountLogin.getUsername() == null || accountLogin.getPassword() == null) {
            return false;
        } else if (accountService.loginByAccount(accountLogin) == null) {
            return false;
        }
        return true;
    }

    @PostMapping("/login")
    public AccountResponseDTO loginByAccount(@RequestBody AccountLoginDTO accountLogin) {
        if (checkAccount(accountLogin)) {
            return accountService.loginByAccount(accountLogin);
        } else {
            throw new ApiInputException("Sai tài khoản");
        }
    }
}
