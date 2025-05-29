package com.example.coursachTrzbd.entity;

import com.example.coursachTrzbd.entity.enums.GoalType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Укажите матч")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @NotNull(message = "Укажите игрока")
    private Player player;

    @Min(value = 1, message = "Минута не может быть меньше 1")
    @Max(value = 130, message = "Минута не может быть больше 130")
    private int minute;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Выберите тип гола")
    private GoalType type;
}
