package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Player;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements CRUDService<Player> {
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    private final PlayerRepository playerRepository;

    @Override
    public Player getById(Integer id) {
        if (playerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Player with id " + id + " not found");
        }
        return playerRepository.findById(id).orElseThrow();
    }

    public List<Player> findByTeamId(Integer teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player save(Player item) {
        playerRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        if (playerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Player with id " + id + " not found");
        }
        playerRepository.deleteById(id);
    }
}
