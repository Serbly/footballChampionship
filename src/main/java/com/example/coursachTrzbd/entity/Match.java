package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "matches")
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    @NotNull(message = "Выберите чемпионат")
    private Championship championship;

    @NotNull(message = "Выберите дату")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    @NotNull(message = "Укажите домашнюю команду")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    @NotNull(message = "Укажите гостевую команду")
    private Team awayTeam;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goals;

    @NotEmpty(message = "Укажите стадион")
    private String stadium;

    @NotEmpty(message = "Счет не может быть пустым")
    private String score;
}
