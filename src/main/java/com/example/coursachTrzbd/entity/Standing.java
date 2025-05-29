package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "standings")
@Data
public class Standing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    @NotNull(message = "Выберите чемпионат")
    private Championship championship;

    @Column(name = "matches_played")
    @Min(value = 0, message = "Колчество матчей не может быть отрицательным")
    private int matchesPlayed;

    @Min(value = 0, message = "Колчество побед не может быть отрицательным")
    private int wins;

    @Min(value = 0, message = "Колчество ничьих не может быть отрицательным")
    private int draws;

    @Min(value = 0, message = "Колчество поражений не может быть отрицательным")
    private int losses;

    @Min(value = 0, message = "Колчество очков не может быть отрицательным")
    private int points;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotNull(message = "Выберите команду")
    private Team team;
}
