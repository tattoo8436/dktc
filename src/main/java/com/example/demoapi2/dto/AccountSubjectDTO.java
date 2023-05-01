package com.example.demoapi2.dto;

import com.example.demoapi2.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSubjectDTO {
    private Long id;
    private SubjectDTO subject;
}
