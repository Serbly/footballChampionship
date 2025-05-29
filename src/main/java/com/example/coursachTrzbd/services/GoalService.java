package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Goal;
import com.example.coursachTrzbd.entity.Match;
import com.example.coursachTrzbd.entity.Player;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.GoalRepository;
import com.example.coursachTrzbd.repositories.MatchRepository;
import com.example.coursachTrzbd.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoalService implements CRUDService<Goal>{
    private final GoalRepository goalRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    @Override
    public Goal getById(Integer id) {
        log.info("Get by id");
        if (goalRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Goal with id " + id + " not found");
        }
        return goalRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Goal> getAll() {
        log.info("Get all");
        return goalRepository.findAll();
    }

    @Override
    public Goal save(Goal item) {
        log.info("Save");
        goalRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete");
        if (goalRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Goal with id " + id + " not found");
        }
        goalRepository.deleteById(id);
    }
}
