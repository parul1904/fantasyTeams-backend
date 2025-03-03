package in.fantasy.teams.repository;

import in.fantasy.teams.entity.Season;
import in.fantasy.teams.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    // all crud database methods
}