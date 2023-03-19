package com.example.demoapi2.service;

import com.example.demoapi2.dto.*;
import com.example.demoapi2.model.Account;
import com.example.demoapi2.model.LayerClass;
import com.example.demoapi2.repository.AccountRepository;
import com.example.demoapi2.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements iAccountService{
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public AccountResponseDTO loginByAccount(String username, String password) {
        List<Account> old = accountRepository.getByUsernameAndPassword(username, password);
        if(old.size() == 0){
            return null;
        } else{
            AccountResponseDTO account = ConvertUtils.convert(old.get(0), AccountResponseDTO.class);
            return account;
        }
    }
}
