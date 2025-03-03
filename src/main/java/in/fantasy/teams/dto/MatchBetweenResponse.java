package in.fantasy.teams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchBetweenResponse {
    private String team1Name;
    private String team2Name;
    private String matchDate;
}