package com.example.demoapi2.controller;

import com.example.demoapi2.dto.AccountResponseDTO;
import com.example.demoapi2.dto.AccountLoginDTO;
import com.example.demoapi2.exception.ApiInputException;
import com.example.demoapi2.service.iAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@RequestParam String path) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
    }
}
