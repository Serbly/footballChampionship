package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
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
    private Championship championship;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goals;

    private String stadium;

    private String score;
}
