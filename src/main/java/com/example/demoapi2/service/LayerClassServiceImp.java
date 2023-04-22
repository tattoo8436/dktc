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
public class LayerClassServiceImp implements iLayerClassService{
    @Autowired
    private LayerClassRepository layerClassRepository;


    @Override
    public LayerClassResponseDTO getLayerClassBySubject(long subjectId) {
        List<LayerClass> old = layerClassRepository.getBySubject(subjectId);
        System.out.println(old);
        List<LayerClassDTO> listLayerClasses = ConvertUtils.convertList(old, LayerClassDTO.class);
        LayerClassResponseDTO layerClass = new LayerClassResponseDTO();
        layerClass.setListLayerClasses(listLayerClasses);
        return layerClass;
    }

    @Override
    public LayerClassResponseDTO getLayerClassByAccount(long accountId) {
        List<LayerClass> old = layerClassRepository.getByAccount(accountId);
        System.out.println(old);
        List<LayerClassDTO> listLayerClasses = ConvertUtils.convertList(old, LayerClassDTO.class);
        LayerClassResponseDTO layerClass = new LayerClassResponseDTO();
        layerClass.setListLayerClasses(listLayerClasses);
        return layerClass;
    }

    @Override
    public void deleteLayerClassesByAccount(long accountId) {
        layerClassRepository.deleteByAccount(accountId);
    }

    @Override
    public void updateById(long id, long accountId) {
        LayerClass layerClass = layerClassRepository.findById(id).get();
        //System.out.println("quantity: " + layerClass.getRemainQuantity());
        if(layerClass.getRemainQuantity() == 1){
            layerClassRepository.updateById(id, accountId);
        }
    }

    @Override
    public LayerClass getLayerClassById(long id) {
        return layerClassRepository.findById(id).get();
    }
}
