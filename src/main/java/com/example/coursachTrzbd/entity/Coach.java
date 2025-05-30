package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "Coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Имя тренера не может быть пустым")
    private String name;

    @NotEmpty(message = "Выберите национальность")
    private String nationality;

    @NotNull(message = "Выберите год рождения")
    private LocalDate birthdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "Имя тренера не может быть пустым") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Имя тренера не может быть пустым") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Выберите национальность") String getNationality() {
        return nationality;
    }

    public void setNationality(@NotEmpty(message = "Выберите национальность") String nationality) {
        this.nationality = nationality;
    }

    public @NotNull(message = "Выберите год рождения") LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NotNull(message = "Выберите год рождения") LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
