package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Team;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements CRUDService<Team> {
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    private final TeamRepository teamRepository;

    @Override
    public Team getById(Integer id) {
        if (teamRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Team with id " + id + " not found");
        }
        return teamRepository.findById(id).orElseThrow();
    }

    public Team findById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team save(Team item) {
        teamRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        if (teamRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Team with id " + id + " not found");
        }
        teamRepository.deleteById(id);
    }
}
