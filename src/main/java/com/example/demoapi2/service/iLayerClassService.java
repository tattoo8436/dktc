package com.example.demoapi2.service;

import com.example.demoapi2.dto.AccountDTO;
import com.example.demoapi2.dto.LayerClassRequestDTO;
import com.example.demoapi2.dto.LayerClassResponseDTO;
import com.example.demoapi2.model.Account;

import java.util.List;

public interface iLayerClassService {

    public LayerClassResponseDTO getLayerClassBySubject(long subjectId);
}
