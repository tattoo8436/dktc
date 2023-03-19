package com.example.demoapi2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LayerClassRequestDTO {

    private String username;

    private String password;

    private long subjectId;
    private List<Long> listLayerClassIds;

}
