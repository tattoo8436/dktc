package com.example.demoapi2.service;

import com.example.demoapi2.dto.AccountDTO;
import com.example.demoapi2.dto.LayerClassRequestDTO;
import com.example.demoapi2.dto.LayerClassResponseDTO;
import com.example.demoapi2.model.Account;

import java.util.List;
import java.util.Optional;

public interface iAccountService {
    //login
    public Account getAccount(String username, String password);
    public List<Account> getAllAccounts();
    public AccountDTO getAccountById(long id);
}
