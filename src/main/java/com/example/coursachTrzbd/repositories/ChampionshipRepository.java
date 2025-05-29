package com.example.coursachTrzbd.repositories;

import com.example.coursachTrzbd.entity.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {
}
