package com.example.coursachTrzbd.repositories;

import com.example.coursachTrzbd.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
}
