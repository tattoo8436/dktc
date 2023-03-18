package com.example.demoapi2.controller;

import com.example.demoapi2.dto.AccountDTO;
import com.example.demoapi2.dto.AccountLoginDTO;
import com.example.demoapi2.dto.LayerClassRequestDTO;
import com.example.demoapi2.dto.LayerClassResponseDTO;
import com.example.demoapi2.exception.ApiInputException;
import com.example.demoapi2.model.Account;
import com.example.demoapi2.service.iAccountService;
import com.example.demoapi2.service.iLayerClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LayerClassController {
    @Autowired
    private iLayerClassService layerClassService;
    private iAccountService accountService;
    private MessageSource messageSource;

    public boolean checkAccount(String username, String password) {
        if (accountService.getAccount(username, password) == null) {
            return false;
        }
        return true;
    }

    @PostMapping("/layer-class/search")
    public LayerClassResponseDTO searchLayerClass(@RequestBody LayerClassRequestDTO layerClass) {
        LayerClassResponseDTO layerClassResponse = layerClassService.getLayerClassBySubject(layerClass.getSubjectId());
        return layerClassResponse;
    }
}
