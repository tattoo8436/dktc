package com.example.demoapi2.service;

import com.example.demoapi2.dto.LayerClassResponseDTO;
import com.example.demoapi2.model.LayerClass;

public interface iLayerClassService {

    public LayerClassResponseDTO getLayerClassBySubject(long subjectId);
    public LayerClassResponseDTO getLayerClassByAccount(long accountId);
    public void deleteLayerClassesByAccount(long accountId);
    public void updateById(long id, long accountId);
    public LayerClass getLayerClassById(long id);
}
