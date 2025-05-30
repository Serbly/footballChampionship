package com.example.coursachTrzbd.entity;

import com.example.coursachTrzbd.entity.enums.PlayerPosition;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "players")
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

    public int getAge() {
        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "Имя не должно быть пустым") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Имя не должно быть пустым") String name) {
        this.name = name;
    }

    @Min(value = 1, message = "Номер не может быть меньше 1")
    @Max(value = 99, message = "Номер не может быть больше 99")
    public int getNumber() {
        return number;
    }

    public void setNumber(@Min(value = 1, message = "Номер не может быть меньше 1") @Max(value = 99, message = "Номер не может быть больше 99") int number) {
        this.number = number;
    }

    public @NotNull(message = "Укажите позицию") PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(@NotNull(message = "Укажите позицию") PlayerPosition position) {
        this.position = position;
    }

    public @NotNull(message = "Выберите команду") Team getTeam() {
        return team;
    }

    public void setTeam(@NotNull(message = "Выберите команду") Team team) {
        this.team = team;
    }

    public @NotEmpty(message = "Укажите национальность") String getNationality() {
        return nationality;
    }

    public void setNationality(@NotEmpty(message = "Укажите национальность") String nationality) {
        this.nationality = nationality;
    }

    public @NotNull(message = "Укажите дату рождения") LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NotNull(message = "Укажите дату рождения") LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
