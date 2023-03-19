package com.example.demoapi2.service;

import com.example.demoapi2.dto.AccountLoginDTO;
import com.example.demoapi2.dto.AccountResponseDTO;
import com.example.demoapi2.model.Account;

import java.util.List;

public interface iAccountService {
    public AccountResponseDTO loginByAccount(String username, String password);
}
