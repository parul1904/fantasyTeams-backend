package in.fantasy.teams.service;

import in.fantasy.teams.dto.TeamDto;

import java.util.List;

public interface TeamService {
    List<TeamDto> getAllTeams();

    TeamDto createTeam(TeamDto team);

    TeamDto getTeamById(Long teamId);

    TeamDto updateTeam(Long teamId, TeamDto teamDto);

    void deleteTeam(Long teamId);
}