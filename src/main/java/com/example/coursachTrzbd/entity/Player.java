package com.example.coursachTrzbd.entity;

import com.example.coursachTrzbd.entity.enums.PlayerPosition;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "players")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_number")
    private int playerNumber;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String nationality;

    private LocalDate birthdate;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goals;
}
