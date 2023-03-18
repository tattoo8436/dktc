package com.example.demoapi2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "layer_class")
public class LayerClass {
    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "groupCode")
    private String groupCode;
    @Column(name = "classCode")
    private String classCode;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "remainQuantity")
    private int remainQuantity;
    @Column(name = "weekdayName")
    private String weekdayName;
    @Column(name = "weekdayValue")
    private int weekdayValue;
    @Column(name = "lessonStart")
    private int lessonStart;
    @Column(name = "lessonNumber")
    private int lessonNumber;
    @Column(name = "room")
    private String room;

}
