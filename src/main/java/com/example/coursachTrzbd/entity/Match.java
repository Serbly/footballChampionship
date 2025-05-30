package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "matches")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Выберите чемпионат") Championship getChampionship() {
        return championship;
    }

    public void setChampionship(@NotNull(message = "Выберите чемпионат") Championship championship) {
        this.championship = championship;
    }

    public @NotNull(message = "Выберите дату") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "Выберите дату") LocalDate date) {
        this.date = date;
    }

    public @NotNull(message = "Укажите домашнюю команду") Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(@NotNull(message = "Укажите домашнюю команду") Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public @NotNull(message = "Укажите гостевую команду") Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(@NotNull(message = "Укажите гостевую команду") Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public @NotEmpty(message = "Укажите стадион") String getStadium() {
        return stadium;
    }

    public void setStadium(@NotEmpty(message = "Укажите стадион") String stadium) {
        this.stadium = stadium;
    }

    public @NotEmpty(message = "Счет не может быть пустым") String getScore() {
        return score;
    }

    public void setScore(@NotEmpty(message = "Счет не может быть пустым") String score) {
        this.score = score;
    }
}
