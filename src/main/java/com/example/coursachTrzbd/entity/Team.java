package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "teams")
@Data
public class Team {
    @Id
    private Integer id;

    @Column(name = "team_name")
    private String teamName;

    private String city;

    private String stadium;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "coach_id")
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
