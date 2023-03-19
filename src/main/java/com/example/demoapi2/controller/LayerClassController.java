package com.example.demoapi2.controller;

import com.example.demoapi2.dto.*;
import com.example.demoapi2.exception.ApiInputException;
import com.example.demoapi2.model.Account;
import com.example.demoapi2.service.iAccountService;
import com.example.demoapi2.service.iLayerClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LayerClassController {
    @Autowired
    private iLayerClassService layerClassService;
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

    @PostMapping("/layer-class/search-by-subjectId")
    public LayerClassResponseDTO searchLayerClassBySubject(@RequestBody LayerClassRequestDTO layerClass) {
        if (checkAccount(layerClass.getUsername(), layerClass.getPassword())) {
            LayerClassResponseDTO layerClassResponse = layerClassService.getLayerClassBySubject(layerClass.getSubjectId());
            return layerClassResponse;
        } else {
            throw new ApiInputException("FBI Warning!!!");
        }
    }

    @PostMapping("/layer-class/search-by-accountId")
    public LayerClassResponseDTO searchLayerClassByAccount(@RequestBody AccountLoginDTO accountLogin) {
        if (checkAccount(accountLogin.getUsername(), accountLogin.getPassword())) {
            AccountResponseDTO account = accountService.loginByAccount(accountLogin.getUsername(), accountLogin.getPassword());
            LayerClassResponseDTO layerClassResponse = layerClassService.getLayerClassByAccount(account.getId());
            return layerClassResponse;
        } else {
            throw new ApiInputException("FBI Warning!!!");
        }
    }

    @PostMapping("/layer-class/save")
    public boolean deleteLayerClassesByAccount(@RequestBody LayerClassRequestDTO layerClassRequest) {
        if (checkAccount(layerClassRequest.getUsername(), layerClassRequest.getPassword())) {
            AccountResponseDTO account = accountService.loginByAccount(layerClassRequest.getUsername(), layerClassRequest.getPassword());
            layerClassService.deleteLayerClassesByAccount(account.getId());
            for(int i = 0; i < layerClassRequest.getListLayerClassIds().size(); i++){
                layerClassService.updateById(layerClassRequest.getListLayerClassIds().get(i), account.getId());
            }
            return true;
        } else {
            throw new ApiInputException("FBI Warning!!!");
        }
    }
}
