package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Player;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.PlayerRepository;
import com.example.coursachTrzbd.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService implements CRUDService<Player> {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Override
    public Player getById(Integer id) {
        log.info("Get by id");
        if (playerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Player with id " + id + " not found");
        }
        return playerRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Player> getAll() {
        log.info("Get all");
        return playerRepository.findAll();
    }

    @Override
    public Player create(Player item) {
        log.info("Create");
        playerRepository.save(item);
        return item;
    }

    @Override
    public Player update(Player item) {
        log.info("Update");
        Player player1 = playerRepository.findById(item.getId()).orElseThrow();
        item.setGoals(player1.getGoals());
        playerRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete");
        if (playerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Player with id " + id + " not found");
        }
        playerRepository.deleteById(id);
    }
}
