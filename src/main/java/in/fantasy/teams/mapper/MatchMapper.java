package in.fantasy.teams.mapper;

import in.fantasy.teams.dto.*;
import in.fantasy.teams.dto.MatchDto;
import in.fantasy.teams.entity.*;
import in.fantasy.teams.repository.PlayerRepository;
import in.fantasy.teams.repository.SeasonRepository;
import in.fantasy.teams.repository.TeamRepository;
import in.fantasy.teams.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MatchMapper {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public MatchDto mapToMatchDto(Match match) {
        return new MatchDto(
                match.getMatchId(),
                match.getSeason().getSeasonId(),
                match.getMatchNo(),
                match.getTeam1().getTeamId(),
                match.getTeam2().getTeamId(),
                match.getVenue().getVenueId(),
                match.getMatchDate(),
                match.getMatchTime(),
                null!=match.getWinnerTeam() ? match.getWinnerTeam().getTeamId() : null,
                match.getWinningMargin(),
                null!=match.getPlayerOfTheMatch() ? match.getPlayerOfTheMatch().getPlayerId() : null,
                null!= match.getMvpId() ? match.getMvpId().getPlayerId() : null
        );
    }

    public Match mapToMatch(MatchDto matchDto) {
        Match match = new Match();
        Season season = seasonRepository.findById(Long.valueOf(matchDto.getSeasonId())).orElseThrow();
        match.setSeason(season);
        match.setMatchNo(matchDto.getMatchNo());
        Team team1 = teamRepository.findById(Long.valueOf(matchDto.getTeam1Id())).orElseThrow();
        match.setTeam1(team1);
        Team team2 = teamRepository.findById(Long.valueOf(matchDto.getTeam2Id())).orElseThrow();
        match.setTeam2(team2);
        Venue venue = venueRepository.findById(Long.valueOf(matchDto.getVenueId())).orElseThrow();
        match.setVenue(venue);
        match.setMatchDate(matchDto.getMatchDate());
        match.setMatchTime(matchDto.getMatchTime());
        if (null != matchDto.getWinnerTeamId()) {
            Team winnerTeam = teamRepository.findById(Long.valueOf(matchDto.getWinnerTeamId())).orElseThrow();
            match.setWinnerTeam(winnerTeam);
        }
        match.setWinningMargin(matchDto.getWinningMargin());
        if (null != matchDto.getPlayerOfTheMatch()) {
            Player playerOfTheMatch = playerRepository.findById(Long.valueOf(matchDto.getPlayerOfTheMatch())).orElseThrow();
            match.setPlayerOfTheMatch(playerOfTheMatch);
        }
        if (null != matchDto.getMvp()) {
            Player mvp = playerRepository.findById(Long.valueOf(matchDto.getMvp())).orElseThrow();
            match.setMvpId(mvp);
        }
        return match;
    }

    public List<MatchResponse> mapToMatchResponse(List<MatchDto> matchDtos) {
        List<MatchResponse> matchResponses = new ArrayList<>();
        for (MatchDto match : matchDtos) {
            MatchResponse response = new MatchResponse();
            Season season = seasonRepository.findById(Long.valueOf(match.getSeasonId())).orElseThrow();
            response.setMatchId(match.getMatchId());
            response.setSeasonYear(season.getYear());
            response.setMatchNo(match.getMatchNo());
            Team team1 = teamRepository.findById(Long.valueOf(match.getTeam1Id())).orElseThrow();
            response.setTeam1(team1.getTeamLogoUrl());
            Team team2 = teamRepository.findById(Long.valueOf(match.getTeam2Id())).orElseThrow();
            response.setTeam2(team2.getTeamLogoUrl());
            Venue venue = venueRepository.findById(Long.valueOf(match.getVenueId())).orElseThrow();
            response.setVenueName(venue.getVenueName());
            response.setMatchDate(match.getMatchDate());
            response.setMatchTime(match.getMatchTime());
            if(null!=match.getWinnerTeamId()) {
                Team winnerTeam = teamRepository.findById(Long.valueOf(match.getWinnerTeamId())).orElseThrow();
                response.setWinnerTeam(winnerTeam.getTeamLogoUrl());
            }
            response.setWinningMargin(match.getWinningMargin());
            if(null!=match.getPlayerOfTheMatch()) {
                Player playerOfTheMatch = playerRepository.findById(Long.valueOf(match.getPlayerOfTheMatch())).orElseThrow();
                response.setPlayerOfTheMatch(playerOfTheMatch.getNickName());
            }
            if(null!=match.getMvp()) {
                Player mvp = playerRepository.findById(Long.valueOf(match.getMvp())).orElseThrow();
                response.setMvp(mvp.getNickName());
            }
            matchResponses.add(response);
        }
        return matchResponses;
    }


    public List<MatchBetweenResponse> mapToMatchBetweenResponse(List<Object[]> result) {
        List<MatchBetweenResponse> matchBetweenResponses = new ArrayList<>();
        for (Object[] row : result) {
            MatchBetweenResponse response = new MatchBetweenResponse();
            response.setTeam1Name((String) row[0]);
            response.setTeam2Name((String) row[1]);
            response.setMatchDate(row[2].toString());
            matchBetweenResponses.add(response);
        }
        return matchBetweenResponses;
    }
}