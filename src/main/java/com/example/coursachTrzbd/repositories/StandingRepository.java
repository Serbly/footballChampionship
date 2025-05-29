package com.example.coursachTrzbd.repositories;

import com.example.coursachTrzbd.entity.Standing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandingRepository extends JpaRepository<Standing, Integer> {
}
