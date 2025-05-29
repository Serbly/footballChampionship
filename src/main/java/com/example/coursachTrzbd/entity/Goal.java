package com.example.coursachTrzbd.entity;

import com.example.coursachTrzbd.entity.enums.GoalType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Goals")
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private int minute;

    @Enumerated(EnumType.STRING)
    private GoalType type;
}
