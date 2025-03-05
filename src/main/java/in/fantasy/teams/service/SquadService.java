package in.fantasy.teams.service;

import in.fantasy.teams.dto.SquadDto;
import in.fantasy.teams.dto.SquadResponse;
import in.fantasy.teams.dto.SquadTeamResponse;

import java.util.List;

public interface SquadService {
    List<SquadResponse> getAllSquads();

    SquadDto createSquad(SquadDto squad);

    SquadDto getSquadById(Long squadId);

    SquadDto updateSquad(Long squadId, SquadDto squadDto);

    void deleteSquad(Long squadId);

    SquadTeamResponse getSquadDetailsByTeam(Long teamId);
}