package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Goal;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.GoalRepository;
import com.example.coursachTrzbd.repositories.MatchRepository;
import com.example.coursachTrzbd.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService implements CRUDService<Goal>{
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository, PlayerRepository playerRepository, MatchRepository matchRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal getById(Integer id) {
        if (goalRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Goal with id " + id + " not found");
        }
        return goalRepository.findById(id).orElseThrow();
    }

    public List<Object[]> findTopScorersByChampionshipAndSeason(Integer championshipId, String season) {
        return goalRepository.findTopScorersByChampionshipAndSeason(championshipId, season);
    }

    @Override
    public List<Goal> getAll() {
        return goalRepository.findAll();
    }

    @Override
    public Goal save(Goal item) {
        goalRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        if (goalRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Goal with id " + id + " not found");
        }
        goalRepository.deleteById(id);
    }
}
