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
    SELECT g.player.name, g.player.team.name, COUNT(g)
    FROM Goal g
    WHERE g.match.championship.id = :championshipId
    GROUP BY g.player.name, g.player.team.name
    ORDER BY COUNT(g) DESC
    """)
    List<Object[]> findTopScorersByChampionshipId(@Param("championshipId") Integer championshipId);

}
