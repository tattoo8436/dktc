package com.example.demoapi2.controller;

import com.example.demoapi2.dto.*;
import com.example.demoapi2.exception.ApiInputException;
import com.example.demoapi2.model.LayerClass;
import com.example.demoapi2.service.AccountService;
import com.example.demoapi2.service.LayerClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LayerClassController {
    @Autowired
    private LayerClassService layerClassService;
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

    @PostMapping("/layer-class/search-by-subjectId")
    public LayerClassResponseDTO searchLayerClassBySubject(@RequestBody LayerClassRequestDTO layerClass) {
        AccountLoginDTO accountLogin = new AccountLoginDTO(layerClass.getUsername(), layerClass.getPassword());
        if (checkAccount(accountLogin)) {
            LayerClassResponseDTO layerClassResponse = layerClassService.getLayerClassBySubject(layerClass.getSubjectId());
            return layerClassResponse;
        } else {
            throw new ApiInputException("Bạn không có quyền!");
        }
    }

    @PostMapping("/layer-class/search-by-accountId")
    public LayerClassResponseDTO searchLayerClassByAccount(@RequestBody AccountLoginDTO accountLogin) {
        if (checkAccount(accountLogin)) {
            AccountResponseDTO account = accountService.loginByAccount(accountLogin);
            LayerClassResponseDTO layerClassResponse = layerClassService.getLayerClassByAccount(account.getId());
            return layerClassResponse;
        } else {
            throw new ApiInputException("Bạn không có quyền!");
        }
    }

    @PostMapping("/layer-class/save")
    public boolean saveLayerClassesByAccount(@RequestBody LayerClassRequestDTO layerClassRequest) {
        AccountLoginDTO accountLogin = new AccountLoginDTO(layerClassRequest.getUsername(), layerClassRequest.getPassword());
        if (checkAccount(accountLogin)) {
            int count = 0;
            for (int i = 0; i < layerClassRequest.getListLayerClassIds().size(); i++) {
                LayerClass layerClass = layerClassService.getLayerClassById(layerClassRequest.getListLayerClassIds().get(i));
                count += layerClass.getSubject().getStc();
            }
            int[][] arr = new int[13][9];
            boolean ok = true;
            for (int i = 0; i < layerClassRequest.getListLayerClassIds().size(); i++) {
                LayerClass layerClass = layerClassService.getLayerClassById(layerClassRequest.getListLayerClassIds().get(i));
                for (int j = 0; j < layerClass.getLessonNumber(); j++) {
                    if (arr[j + layerClass.getLessonStart()][layerClass.getWeekdayValue()] == 0) {
                        arr[j + layerClass.getLessonStart()][layerClass.getWeekdayValue()] = 1;
                        continue;
                    } else {
                        ok = false;
                        break;
                    }
                }
            }

            if (count < 12) {
                throw new ApiInputException("Không đủ 12 tín chỉ!");
            } else if (ok == false) {
                throw new ApiInputException("Trùng thời khoá biểu!");
            } else {
                AccountResponseDTO account = accountService.loginByAccount(accountLogin);
                layerClassService.deleteLayerClassesByAccount(account.getId());
                for (int i = 0; i < layerClassRequest.getListLayerClassIds().size(); i++) {
                    layerClassService.updateById(layerClassRequest.getListLayerClassIds().get(i), account.getId());
                }
                return true;
            }
        } else {
            throw new ApiInputException("Bạn không có quyền!");
        }
    }
}
