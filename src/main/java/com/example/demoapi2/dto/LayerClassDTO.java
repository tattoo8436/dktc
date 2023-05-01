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
    private Long id;
    private String groupCode;
    private String classCode;
    private Integer quantity;
    private Integer remainQuantity;
    private String weekdayName;
    private Integer weekdayValue;
    private Integer lessonStart;
    private Integer lessonNumber;
    private String room;
    private SubjectDTO subject;
    private Long accountId;

}
