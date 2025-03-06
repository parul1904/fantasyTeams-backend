package in.fantasy.teams.service;

import in.fantasy.teams.dto.MatchDto;
import in.fantasy.teams.response.DreamTeamResponse;
import in.fantasy.teams.response.MatchBetweenResponse;
import in.fantasy.teams.response.MatchResponse;

import java.util.List;

public interface DreamTeamService {
    List<DreamTeamResponse> getDreamTeamByMatchNo(Integer matchNo);
}