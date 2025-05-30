package com.example.coursachTrzbd.repositories;

import com.example.coursachTrzbd.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    @Query("""
    SELECT g.player.name, COUNT(g.id) as total
    FROM Goal g
    WHERE g.match.championship.id = :championshipId
      AND g.match.championship.season = :season
    GROUP BY g.player.name
    ORDER BY total DESC
    """)
    List<Object[]> findTopScorersByChampionshipAndSeason(
            @Param("championshipId") Integer championshipId,
            @Param("season") String season
    );

}
