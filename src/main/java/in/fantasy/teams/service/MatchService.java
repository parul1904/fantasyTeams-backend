package in.fantasy.teams.service;

import in.fantasy.teams.response.MatchBetweenResponse;
import in.fantasy.teams.dto.MatchDto;
import in.fantasy.teams.response.MatchResponse;

import java.util.List;

public interface MatchService {
    MatchDto createMatch(MatchDto matchDto);
    MatchDto getMatchById(Integer id);
    List<MatchResponse> getAllMatches();
    MatchDto updateMatch(Integer id, MatchDto matchDto);
    void deleteMatch(Integer id);

    List<MatchBetweenResponse> getMatchDetailsByMatchId(Integer seasonId);
} 