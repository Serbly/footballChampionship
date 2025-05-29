package com.example.coursachTrzbd.services;

import com.example.coursachTrzbd.entity.Standing;
import com.example.coursachTrzbd.error.NotFoundException;
import com.example.coursachTrzbd.repositories.StandingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StandingService implements CRUDService<Standing> {
    private final StandingRepository standingRepository;

    @Override
    public Standing getById(Integer id) {
        log.info("Get by id");
        if (standingRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Standing with id " + id + " not found");
        }
        return standingRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Standing> getAll() {
        log.info("Get all");
        return standingRepository.findAll();
    }

    @Override
    public Standing save(Standing item) {
        log.info("Save");
        standingRepository.save(item);
        return item;
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete");
        if (standingRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Standing with id " + id + " not found");
        }
        standingRepository.deleteById(id);
    }
}
