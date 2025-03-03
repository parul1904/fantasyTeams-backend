package in.fantasy.teams.mapper;

import in.fantasy.teams.dto.PlayerDto;
import in.fantasy.teams.dto.TeamDto;
import in.fantasy.teams.entity.Player;
import in.fantasy.teams.entity.Team;

public class TeamMapper {

    public static TeamDto mapToTeamDto(Team team) {
        return new TeamDto(
                team.getTeamId(),
                team.getTeamName(),
                team.getTeamShortName(),
                team.getTeamLogoUrl(),
                team.getCaptain(),
                team.getCoach(),
                team.getVenue(),
                team.getTitleWon()
        );
    }

    public static Team mapToTeam(TeamDto teamDto) {
        return new Team(
                teamDto.getTeamId(),
                teamDto.getTeamName(),
                teamDto.getTeamShortName(),
                teamDto.getTeamLogoUrl(),
                teamDto.getCaptain(),
                teamDto.getCoach(),
                teamDto.getVenue(),
                teamDto.getTitleWon()
        );
    }
}