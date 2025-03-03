package in.fantasy.teams.service;

import in.fantasy.teams.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> getAllPlayers();

    PlayerDto createPlayer(PlayerDto player);

    PlayerDto getPlayerById(Long employeeId);

    PlayerDto updatePlayer(Long playerId, PlayerDto playerDto);

    void deletePlayer(Long playerId);
}