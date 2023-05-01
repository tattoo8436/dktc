package com.example.demoapi2.service;

import com.example.demoapi2.dto.*;
import com.example.demoapi2.model.Account;
import com.example.demoapi2.repository.AccountRepository;
import com.example.demoapi2.utils.ConvertUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountResponseDTO loginByAccount(AccountLoginDTO accountLogin) {
        List<Account> old = accountRepository.getByUsernameAndPassword(accountLogin.getUsername(), accountLogin.getPassword());
        if (old.size() == 0) {
            return null;
        } else {
            AccountResponseDTO account = ConvertUtils.convert(old.get(0), AccountResponseDTO.class);
            return account;
        }
    }
}
