package in.fantasy.teams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {
    private Integer matchId;
    private Integer seasonId;
    private Integer matchNo;
    private Integer team1Id;
    private Integer team2Id;
    private Integer venueId;
    private LocalDate matchDate;
    private String matchTime;
    private Integer winnerTeamId;
    private String winningMargin;
    private Integer playerOfTheMatch;
    private Integer mvp;
} 