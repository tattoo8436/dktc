package com.example.demoapi2.service;

import com.example.demoapi2.dto.LayerClassDTO;
import com.example.demoapi2.dto.LayerClassResponseDTO;
import com.example.demoapi2.model.LayerClass;
import com.example.demoapi2.repository.LayerClassRepository;
import com.example.demoapi2.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayerClassService {
    @Autowired
    private LayerClassRepository layerClassRepository;


    public LayerClassResponseDTO getLayerClassBySubject(long subjectId) {
        List<LayerClass> old = layerClassRepository.getBySubject(subjectId);
        System.out.println(old);
        List<LayerClassDTO> listLayerClasses = ConvertUtils.convertList(old, LayerClassDTO.class);
        LayerClassResponseDTO layerClass = new LayerClassResponseDTO();
        layerClass.setListLayerClasses(listLayerClasses);
        return layerClass;
    }

    public LayerClassResponseDTO getLayerClassByAccount(long accountId) {
        List<LayerClass> old = layerClassRepository.getByAccount(accountId);
        System.out.println(old);
        List<LayerClassDTO> listLayerClasses = ConvertUtils.convertList(old, LayerClassDTO.class);
        LayerClassResponseDTO layerClass = new LayerClassResponseDTO();
        layerClass.setListLayerClasses(listLayerClasses);
        return layerClass;
    }

    public void deleteLayerClassesByAccount(long accountId) {
        layerClassRepository.deleteByAccount(accountId);
    }

    public void updateById(long id, long accountId) {
        LayerClass layerClass = layerClassRepository.findById(id).get();
        //System.out.println("quantity: " + layerClass.getRemainQuantity());
        if (layerClass.getRemainQuantity() == 1) {
            layerClassRepository.updateById(id, accountId);
        } else if(layerClass.getRemainQuantity() == 0 && layerClass.getAccount().getId() == accountId){
            layerClassRepository.updateById(id, accountId);
        }
    }

    public LayerClass getLayerClassById(long id) {
        return layerClassRepository.findById(id).get();
    }
}
