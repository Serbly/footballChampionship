package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Standing;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.StandingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingService implements CRUDService<Standing> {
    public StandingService(StandingRepository standingRepository) {
        this.standingRepository = standingRepository;
    }

    private final StandingRepository standingRepository;

    @Override
    public Standing getById(Integer id) {
        if (standingRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Standing with id " + id + " not found");
        }
        return standingRepository.findById(id).orElseThrow();
    }

    public List<Standing> findByChampionshipId(Integer championshipId) {
        return standingRepository.findByChampionshipId(championshipId);
    }

    public List<Standing> findByChampionshipAndSeason(Integer championshipId, String season) {
        return standingRepository.findByChampionshipAndSeason(championshipId, season);
    }

    @Override
    public List<Standing> getAll() {
        return standingRepository.findAll();
    }

    @Override
    public Standing save(Standing item) {
        standingRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        if (standingRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Standing with id " + id + " not found");
        }
        standingRepository.deleteById(id);
    }
}
