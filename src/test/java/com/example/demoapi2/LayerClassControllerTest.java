package com.example.demoapi2;

import com.example.demoapi2.controller.AccountController;
import com.example.demoapi2.controller.LayerClassController;
import com.example.demoapi2.dto.AccountLoginDTO;
import com.example.demoapi2.dto.AccountResponseDTO;
import com.example.demoapi2.dto.LayerClassRequestDTO;
import com.example.demoapi2.dto.LayerClassResponseDTO;
import com.example.demoapi2.exception.ApiInputException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LayerClassControllerTest{
    @Autowired
    private LayerClassController layerClassController;

    @Test
    public void testGetLayerClassBySubjectSuccess() {
        LayerClassRequestDTO layerClassRequest = new LayerClassRequestDTO("test", "test", 1L, null);
        LayerClassResponseDTO result = layerClassController.searchLayerClassBySubject(layerClassRequest);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetLayerClassByAccountSuccess() {
        AccountLoginDTO accountLogin = new AccountLoginDTO("test", "test");
        LayerClassResponseDTO result = layerClassController.searchLayerClassByAccount(accountLogin);
        //System.out.println(result.getListLayerClasses().size());
        Assert.assertNotNull(result);
    }

    @Test
    public void testSaveLayerClassSuccess() {
        List<Long> arr = Arrays.asList(44L, 45L, 46L, 47L);
        LayerClassRequestDTO layerClassRequest = new LayerClassRequestDTO("test", "test", null, arr);
        boolean result = layerClassController.saveLayerClassesByAccount(layerClassRequest);
        Assert.assertTrue(result);
    }

    @Test(expected = ApiInputException.class)
    public void testSaveLayerClassLessThan12() {
        List<Long> arr = Arrays.asList(44L, 45L);
        LayerClassRequestDTO layerClassRequest = new LayerClassRequestDTO("test", "test", null, arr);
        layerClassController.saveLayerClassesByAccount(layerClassRequest);
    }

    @Test(expected = ApiInputException.class)
    public void testSaveLayerClassSameTimetable() {
        List<Long> arr = Arrays.asList(44L, 45L, 46L, 47L, 38L);
        LayerClassRequestDTO layerClassRequest = new LayerClassRequestDTO("test", "test", null, arr);
        layerClassController.saveLayerClassesByAccount(layerClassRequest);
    }

    @Test
    public void testSaveLayerClassAlreadySelected() throws InterruptedException {
        List<Long> arr = Arrays.asList(44L, 45L, 46L);
        LayerClassRequestDTO layerClassRequest = new LayerClassRequestDTO("test2", "test2", null, arr);
        AccountLoginDTO accountLogin = new AccountLoginDTO(layerClassRequest.getUsername(), layerClassRequest.getPassword());
        layerClassController.saveLayerClassesByAccount(layerClassRequest);
        LayerClassResponseDTO result = layerClassController.searchLayerClassByAccount(accountLogin);
        Assert.assertEquals(0, result.getListLayerClasses().size());
    }
}
