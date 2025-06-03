package com.example.coursachTrzbd.repositories;

import com.example.coursachTrzbd.entity.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {
    @Query("SELECT DISTINCT c.season FROM Championship c WHERE c.name = :name ORDER BY c.season DESC")
    List<String> findDistinctSeasonsByChampionshipName(@Param("name") String name);

    @Query("SELECT c FROM Championship c WHERE c.name = :name AND c.season = :season")
    Championship findByNameAndSeason(@Param("name") String name, @Param("season") String season);
}
