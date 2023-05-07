package com.example.demoapi2;

import com.example.demoapi2.controller.AccountController;
import com.example.demoapi2.dto.AccountLoginDTO;
import com.example.demoapi2.dto.AccountResponseDTO;
import com.example.demoapi2.exception.ApiInputException;
import com.example.demoapi2.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountControllerTest {
    @Autowired
    private AccountController accountController;
    @Test
    public void testLoginByAccountSuccess() {
        AccountLoginDTO accountLogin = new AccountLoginDTO("test", "test");
        AccountResponseDTO result = accountController.loginByAccount(accountLogin);
        Assert.assertNotNull(result);
    }
    @Test(expected = ApiInputException.class)
    public void testLoginByAccountFail() {
        AccountLoginDTO accountLogin = new AccountLoginDTO("test", "testtt");
        AccountResponseDTO result = accountController.loginByAccount(accountLogin);
    }
    @Test(expected = ApiInputException.class)
    public void testLoginByAccountNullUsername() {
        AccountLoginDTO accountLogin = new AccountLoginDTO(null, "test");
        AccountResponseDTO result = accountController.loginByAccount(accountLogin);
    }
    @Test(expected = ApiInputException.class)
    public void testLoginByAccountNullPassword() {
        AccountLoginDTO accountLogin = new AccountLoginDTO("test", null);
        AccountResponseDTO result = accountController.loginByAccount(accountLogin);
    }
    @Test(expected = ApiInputException.class)
    public void testLoginByAccountNullUsernameAndPassword() {
        AccountLoginDTO accountLogin = new AccountLoginDTO(null, null);
        AccountResponseDTO result = accountController.loginByAccount(accountLogin);
    }
}
