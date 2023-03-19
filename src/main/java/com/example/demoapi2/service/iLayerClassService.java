package com.example.demoapi2.service;

import com.example.demoapi2.dto.LayerClassResponseDTO;

public interface iLayerClassService {

    public LayerClassResponseDTO getLayerClassBySubject(long subjectId);
    public LayerClassResponseDTO getLayerClassByAccount(long accountId);
    public void deleteLayerClassesByAccount(long accountId);
    public void updateById(long id, long accountId);
}
