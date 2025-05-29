package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Coaches")
@Data
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotEmpty(message = "Имя тренера не может быть пустым")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "team_id")
    private Team team;

    @NotEmpty(message = "Выберите национальность")
    private String nationality;

    @NotNull(message = "Выберите год рождения")
    private LocalDate birthdate;
}
