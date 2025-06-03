package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Championship;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.ChampionshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionshipService implements CRUDService<Championship> {
    public ChampionshipService(ChampionshipRepository repository) {
        this.repository = repository;
    }

    private final ChampionshipRepository repository;

    @Override
    public Championship getById(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Championship with id " + id + " not found");
        }
        return repository.findById(id).orElseThrow();
    }

    public List<String> findSeasonsByChampionshipName(String name) {
        return repository.findDistinctSeasonsByChampionshipName(name);
    }

    public Championship getByNameAndSeason(String name, String season) {
        return repository.findByNameAndSeason(name, season);
    }

    public List<Championship> getUniqueChampionships() {
        return repository.findAll().stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Championship::getName, c -> c, (c1, c2) -> c1),
                        map -> new ArrayList<>(map.values())
                ));
    }


    @Override
    public List<Championship> getAll() {
        return repository.findAll();
    }

    @Override
    public Championship save(Championship item) {
        repository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Championship with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
