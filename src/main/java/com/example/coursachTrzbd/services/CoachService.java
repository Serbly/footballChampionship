package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Coach;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.CoachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService implements CRUDService<Coach> {
    public CoachService(CoachRepository repository) {
        this.repository = repository;
    }

    private final CoachRepository repository;

    @Override
    public Coach getById(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Coach with id " + id + " not found");
        }
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Coach> getAll() {
        return repository.findAll();
    }

    @Override
    public Coach save(Coach item) {
        repository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Coach with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
