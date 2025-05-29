package com.example.coursachTrzbd.repositories;

import com.example.coursachTrzbd.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
