package com.example.demoapi2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "stc")
    private Integer stc;
    @OneToMany(mappedBy = "subject")
    private List<AccountSubject> listAccountSubjects;
    @OneToMany(mappedBy = "subject")
    private List<LayerClass> listLayerClasses;
}
