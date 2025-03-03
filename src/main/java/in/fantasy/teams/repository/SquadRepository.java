package in.fantasy.teams.repository;

import in.fantasy.teams.entity.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadRepository extends JpaRepository<Squad, Long> {
    // all crud database methods
}