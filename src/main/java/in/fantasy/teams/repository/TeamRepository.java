package in.fantasy.teams.repository;

import in.fantasy.teams.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // all crud database methods
}