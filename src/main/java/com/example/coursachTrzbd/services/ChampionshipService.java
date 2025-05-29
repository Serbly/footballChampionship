package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Championship;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.ChampionshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChampionshipService implements CRUDService<Championship> {
    private final ChampionshipRepository repository;

    @Override
    public Championship getById(Integer id) {
        log.info("Get by id");
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Championship with id " + id + " not found");
        }
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Championship> getAll() {
        log.info("Get all");
        return repository.findAll();
    }

    @Override
    public Championship create(Championship item) {
        log.info("Create");
        repository.save(item);
        return item;
    }

    @Override
    public Championship update(Championship item) {
        log.info("Update");
        Championship championship = repository.findById(item.getId()).orElseThrow();
        item.setStandings(championship.getStandings());
        item.setMatches(championship.getMatches());
        repository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete");
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Championship with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
