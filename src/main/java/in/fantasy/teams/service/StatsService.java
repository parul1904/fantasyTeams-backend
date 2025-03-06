package in.fantasy.teams.service;

import in.fantasy.teams.dto.*;
import in.fantasy.teams.response.ListStatsResponse;
import in.fantasy.teams.response.StatsPerMatchResponse;
import in.fantasy.teams.response.StatsPerPlayerResponse;

import java.util.List;

public interface StatsService {
    StatsDto createStats(StatsDto statsDto);
    StatsDto getStatsById(Integer id);

    List<ListStatsResponse> getAllStats();

    List<StatsPerMatchResponse> getAllStatsByMatchId(Integer matchId);
    StatsDto updateStats(Integer id, StatsDto statsDto);
    void deleteStats(Integer id);

    StatsPerPlayerResponse getPlayerStatsByPlayerId(Integer playerId);
} 