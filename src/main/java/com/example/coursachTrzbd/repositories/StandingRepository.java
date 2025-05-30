package com.example.coursachTrzbd.repositories;

import com.example.coursachTrzbd.entity.Standing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandingRepository extends JpaRepository<Standing, Integer> {
    List<Standing> findByChampionshipId(Integer championshipId);

    @Query("""
    SELECT s FROM Standing s
    WHERE s.championship.id = :championshipId
      AND s.championship.season = :season
""")
    List<Standing> findByChampionshipAndSeason(
            @Param("championshipId") Integer championshipId,
            @Param("season") String season
    );

}
