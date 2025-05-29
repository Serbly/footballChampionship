package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Coaches")
@Data
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "coach_name")
    private String coachName;

    private String nationality;

    private LocalDate birthdate;
}
