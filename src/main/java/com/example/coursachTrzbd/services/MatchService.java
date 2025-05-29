package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Match;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchService implements CRUDService<Match> {
    private final MatchRepository matchRepository;

    @Override
    public Match getById(Integer id) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Match with id " + id + " not found");
        }
        return matchRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    @Override
    public Match save(Match item) {
        matchRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Match with id " + id + " not found");
        }
        matchRepository.deleteById(id);
    }
}