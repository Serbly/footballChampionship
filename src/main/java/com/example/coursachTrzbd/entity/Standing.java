package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "standings")
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

    @Min(value = 0, message = "Колчество матчей не может быть отрицательным")
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(@Min(value = 0, message = "Колчество матчей не может быть отрицательным") int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @Min(value = 0, message = "Колчество побед не может быть отрицательным")
    public int getWins() {
        return wins;
    }

    public void setWins(@Min(value = 0, message = "Колчество побед не может быть отрицательным") int wins) {
        this.wins = wins;
    }

    @Min(value = 0, message = "Колчество ничьих не может быть отрицательным")
    public int getDraws() {
        return draws;
    }

    public void setDraws(@Min(value = 0, message = "Колчество ничьих не может быть отрицательным") int draws) {
        this.draws = draws;
    }

    @Min(value = 0, message = "Колчество поражений не может быть отрицательным")
    public int getLosses() {
        return losses;
    }

    public void setLosses(@Min(value = 0, message = "Колчество поражений не может быть отрицательным") int losses) {
        this.losses = losses;
    }

    @Min(value = 0, message = "Колчество очков не может быть отрицательным")
    public int getPoints() {
        return points;
    }

    public void setPoints(@Min(value = 0, message = "Колчество очков не может быть отрицательным") int points) {
        this.points = points;
    }

    public @NotNull(message = "Выберите команду") Team getTeam() {
        return team;
    }

    public void setTeam(@NotNull(message = "Выберите команду") Team team) {
        this.team = team;
    }
}
