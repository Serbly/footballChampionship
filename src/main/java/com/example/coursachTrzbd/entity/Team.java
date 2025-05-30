package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Название команды не может быть пустым")
    private String name;

    @NotEmpty(message = "Укажите название города")
    private String city;

    @NotEmpty(message = "Укажите название стадиона")
    private String stadium;

    @OneToOne(fetch = FetchType.LAZY)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "Название команды не может быть пустым") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Название команды не может быть пустым") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Укажите название города") String getCity() {
        return city;
    }

    public void setCity(@NotEmpty(message = "Укажите название города") String city) {
        this.city = city;
    }

    public @NotEmpty(message = "Укажите название стадиона") String getStadium() {
        return stadium;
    }

    public void setStadium(@NotEmpty(message = "Укажите название стадиона") String stadium) {
        this.stadium = stadium;
    }

    public @NotNull(message = "Выберите тренера") Coach getCoach() {
        return coach;
    }

    public void setCoach(@NotNull(message = "Выберите тренера") Coach coach) {
        this.coach = coach;
    }

    public List<Match> getHomeMatches() {
        return homeMatches;
    }

    public void setHomeMatches(List<Match> homeMatches) {
        this.homeMatches = homeMatches;
    }

    public List<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(List<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Standing> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing> standings) {
        this.standings = standings;
    }
}
