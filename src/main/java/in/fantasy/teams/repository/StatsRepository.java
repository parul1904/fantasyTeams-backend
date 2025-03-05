package in.fantasy.teams.repository;

import in.fantasy.teams.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query(value = "SELECT * FROM fantasyteams.match_stats s WHERE s.match_no = :matchId ORDER BY s.total_point_dream11_old_system DESC", nativeQuery = true)
    List<Object[]> getAllStatsByMatchId(@Param("matchId") Long matchId);

    @Query(value= "SELECT ms.match_stats_id, s.year, m.match_no, p.player_name, p.player_img_url, p.role, p.country,\n" +
            "\t\t\tms.runs_scored, ms.ball_faced, ms.fours, ms.sixes, ms.strike_rate, \n" +
            "\t\t\tms.overs, ms.total_wickets, ms.runs_conceded, ms.economy_rate,\n" +
            "\t\t\tms.catch_taken, ms.stumping, ms.is_impact_player\n" +
            "\t\tFROM match_stats ms\n" +
            "\t\tJOIN seasons s ON ms.season_id = s.season_id\n" +
            "\t\tJOIN matches m ON ms.match_no = m.match_id\n" +
            "\t\tJOIN players p ON ms.player_id = p.player_id " +
            "WHERE ms.player_id = :playerId", nativeQuery = true)
    List<Object[]> getPlayerStatsByPlayerId(@Param("playerId") Long playerId);
}