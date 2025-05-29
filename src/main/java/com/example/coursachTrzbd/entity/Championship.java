package com.example.coursachTrzbd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "Championships")
@Data
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Название не может быть пустым")
    private String name;

    @NotEmpty(message = "Укажите сезон")
    private String season;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matches;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Standing> standings;
}
