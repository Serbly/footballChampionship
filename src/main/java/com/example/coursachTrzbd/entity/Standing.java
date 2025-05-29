package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
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
    private Championship championship;

    @Column(name = "matches_played")
    private int matchesPlayed;

    private int wins;

    private int draws;

    private int losses;

    private int points;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
