package com.example.demoapi2.service;

import com.example.demoapi2.dto.*;
import com.example.demoapi2.model.Account;
import com.example.demoapi2.model.AccountSubject;
import com.example.demoapi2.model.LayerClass;
import com.example.demoapi2.model.Subject;
import com.example.demoapi2.repository.AccountRepository;
import com.example.demoapi2.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImp implements iAccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account getAccount(String username, String password) {
        if(accountRepository.getByUsernameAndPassword(username, password).size() != 0){
            return accountRepository.getByUsernameAndPassword(username, password).get(0);
        }
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountDTO getAccountById(long id) {
        Account old = accountRepository.findById(id).get();
        System.out.println(old);
        AccountDTO account = ConvertUtils.convert(old, AccountDTO.class);
        return account;
    }

}
