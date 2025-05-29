package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Coach;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.CoachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoachService implements CRUDService<Coach> {
    private final CoachRepository repository;

    @Override
    public Coach getById(Integer id) {
        log.info("Get by id");
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Coach with id " + id + " not found");
        }
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Coach> getAll() {
        log.info("Get all");
        return repository.findAll();
    }

    @Override
    public Coach save(Coach item) {
        log.info("Save");
        repository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete");
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Coach with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
