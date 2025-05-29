package com.example.coursachTrzbd.entity;

import com.example.coursachTrzbd.entity.enums.PlayerPosition;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;

    @Min(value = 1, message = "Номер не может быть меньше 1")
    @Max(value = 99, message = "Номер не может быть больше 99")
    private int number;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Укажите позицию")
    private PlayerPosition position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotNull(message = "Выберите команду")
    private Team team;

    @NotEmpty(message = "Укажите национальность")
    private String nationality;

    @NotNull(message = "Укажите дату рождения")
    private LocalDate birthdate;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goals;
}
