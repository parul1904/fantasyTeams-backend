package in.fantasy.teams.repository;

import in.fantasy.teams.entity.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SquadRepository extends JpaRepository<Squad, Long> {

    @Query(value = "SELECT * FROM fantasyteams.squads s WHERE s.season_id =:seasonId AND s.team_Id =:teamId", nativeQuery = true)
    List<Object[]> findBySeasonIdAndTeamId(Integer seasonId, Integer teamId);

}