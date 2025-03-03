package in.fantasy.teams.dto;

import in.fantasy.teams.entity.Player;
import in.fantasy.teams.entity.Season;
import in.fantasy.teams.entity.Team;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquadDto {
    private Integer squadId;
    private Integer seasonId;
    private Integer teamId;
    private Integer playerId;
}