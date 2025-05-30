package com.example.coursachTrzbd.repositories;

import com.example.coursachTrzbd.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    @Query("""
    SELECT m FROM Match m
    WHERE (m.homeTeam.id = :id OR m.awayTeam.id = :id)
      AND m.championship.season = :season
    """)
    List<Match> findByTeamAndSeason(@Param("id") Integer id, @Param("season") String season);
}
