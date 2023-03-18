package com.example.demoapi2.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LayerClassDTO {
    private long id;
    private String groupCode;
    private String classCode;
    private int quantity;
    private int remainQuantity;
    private String weekdayName;
    private int weekdayValue;
    private int lessonStart;
    private int lessonNumber;
    private String room;
    private SubjectDTO subject;

}
