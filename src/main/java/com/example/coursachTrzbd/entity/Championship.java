package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Championships")
@Data
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "championship_name")
    private String championshipName;

    private String season;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matches;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Standing> standings;
}
