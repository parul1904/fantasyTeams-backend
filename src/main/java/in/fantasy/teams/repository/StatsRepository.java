package in.fantasy.teams.repository;

import in.fantasy.teams.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query(value = "SELECT * FROM fantasyteams.match_stats s WHERE s.match_no = :matchId", nativeQuery = true)
    List<Object[]> getAllStatsByMatchId(@Param("matchId") Long matchId);
}