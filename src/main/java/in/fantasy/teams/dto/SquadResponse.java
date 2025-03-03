package in.fantasy.teams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SquadResponse {
    private Integer squadId;
    private Integer seasonYear;
    private String teamLogo;
    private String playerName;
    private String playerImage;
}