package in.fantasy.teams.repository;

import in.fantasy.teams.entity.Player;
import in.fantasy.teams.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    // all crud database methods
}