package com.example.coursachTrzbd.entity;

import com.example.coursachTrzbd.entity.enums.GoalType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    @NotNull(message = "Укажите чемпионат")
    private Championship championship;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Укажите матч") Match getMatch() {
        return match;
    }

    public void setMatch(@NotNull(message = "Укажите матч") Match match) {
        this.match = match;
    }

    public @NotNull(message = "Укажите игрока") Player getPlayer() {
        return player;
    }

    public void setPlayer(@NotNull(message = "Укажите игрока") Player player) {
        this.player = player;
    }

    @Min(value = 1, message = "Минута не может быть меньше 1")
    @Max(value = 130, message = "Минута не может быть больше 130")
    public int getMinute() {
        return minute;
    }

    public void setMinute(@Min(value = 1, message = "Минута не может быть меньше 1") @Max(value = 130, message = "Минута не может быть больше 130") int minute) {
        this.minute = minute;
    }

    public @NotNull(message = "Выберите тип гола") GoalType getType() {
        return type;
    }

    public void setType(@NotNull(message = "Выберите тип гола") GoalType type) {
        this.type = type;
    }

    public Championship getChampionship() { return championship; }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
