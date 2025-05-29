package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "teams")
@Data
public class Team {
    @Id
    private Integer id;

    @NotEmpty(message = "Название команды не может быть пустым")
    private String name;

    @NotEmpty(message = "Укажите название города")
    private String city;

    @NotEmpty(message = "Укажите название стадиона")
    private String stadium;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "coach_id")
    @NotNull(message = "Выберите тренера")
    private Coach coach;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> awayMatches;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Standing> standings;
}
