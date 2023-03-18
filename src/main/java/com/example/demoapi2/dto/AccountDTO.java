package com.example.demoapi2.dto;

import com.example.demoapi2.model.LayerClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private long id;

    private String username;

    private String password;

    private String name;

    private List<LayerClassDTO> listLayerClasses;

    private List<AccountSubjectDTO> listAccountSubjects;
}
