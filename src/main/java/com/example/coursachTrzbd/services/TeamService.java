package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Team;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamService implements CRUDService<Team> {
    private final TeamRepository teamRepository;

    @Override
    public Team getById(Integer id) {
        if (teamRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Team with id " + id + " not found");
        }
        return teamRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team create(Team item) {
        teamRepository.save(item);
        return item;
    }

    @Override
    public Team update(Team item) {
        Team team1 = teamRepository.findById(item.getId()).orElseThrow();
        item.setStandings(team1.getStandings());
        item.setPlayers(team1.getPlayers());
        item.setHomeMatches(team1.getHomeMatches());
        item.setAwayMatches(team1.getAwayMatches());
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
