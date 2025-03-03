package in.fantasy.teams.mapper;

import in.fantasy.teams.dto.*;
import in.fantasy.teams.entity.*;
import in.fantasy.teams.repository.MatchRepository;
import in.fantasy.teams.repository.PlayerRepository;
import in.fantasy.teams.repository.SeasonRepository;
import in.fantasy.teams.repository.TeamRepository;
import in.fantasy.teams.util.NewPointCalculator;
import in.fantasy.teams.util.OldPointCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StatsMapper {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    public StatsDto mapToStatsDto(Stats stats) {
        return new StatsDto(
                stats.getMatchStatsId(),
                stats.getSeason().getSeasonId(),
                stats.getMatchNo().getMatchNo(),
                stats.getPlayerId().getPlayerId(),
                stats.getRunsScored(),
                stats.getBallFaced(),
                stats.getFours(),
                stats.getSixes(),
                stats.getStrikeRate(),
                stats.getOvers(),
                stats.getTotalWickets(),
                stats.getBowledLbw(),
                stats.getOtherDismissal(),
                stats.getDots(),
                stats.getMaiden(),
                stats.getEconomyRate(),
                stats.getCatchTaken(),
                stats.getStumping(),
                stats.getDirectRunout(),
                stats.getInDirectRunout(),
                stats.getIsImpactPlayer(),
                stats.getTotalPointNewSystem(),
                stats.getTotalPointOldSystem()
        );
    }

    public Stats mapToStats(StatsDto statsDto) {
        Stats stats = new Stats();
        stats.setSeason(seasonRepository.findById(Long.valueOf(statsDto.getSeasonId())).orElseThrow());
        stats.setMatchNo(matchRepository.findById(Math.toIntExact(Long.valueOf(statsDto.getMatchId()))).orElseThrow());
        Optional<Player> playerDetails = playerRepository.findById(Long.valueOf(statsDto.getPlayerId()));
        stats.setPlayerId(playerDetails.orElseThrow());
        stats.setRunsScored(statsDto.getRunsScored());
        stats.setBallFaced(statsDto.getBallFaced());
        stats.setFours(statsDto.getFours());
        stats.setSixes(statsDto.getSixes());
        stats.setStrikeRate(statsDto.getStrikeRate());
        stats.setOvers(statsDto.getOvers());
        stats.setTotalWickets(statsDto.getTotalWickets());
        stats.setBowledLbw(statsDto.getBowledLbw());
        stats.setOtherDismissal(statsDto.getOtherDismissal());
        stats.setDots(statsDto.getDots());
        stats.setMaiden(statsDto.getMaiden());
        stats.setEconomyRate(statsDto.getEconomyRate());
        stats.setCatchTaken(statsDto.getCatchTaken());
        stats.setStumping(statsDto.getStumping());
        stats.setDirectRunout(statsDto.getDirectRunout());
        stats.setInDirectRunout(statsDto.getInDirectRunout());
        stats.setIsImpactPlayer(statsDto.getIsImpactPlayer());
        int totalPointsForNewSystem = NewPointCalculator.calculatePoints(statsDto, playerDetails.get().getRole());
        stats.setTotalPointNewSystem(totalPointsForNewSystem);
        int totalPointsForOldSystem = OldPointCalculator.calculatePoints(statsDto, playerDetails.get().getRole());
        stats.setTotalPointOldSystem(totalPointsForOldSystem);
        return stats;
    }

    public List<ListStatsResponse> mapToStatsResponseList(List<StatsDto> statsList) {
        List<ListStatsResponse> statsResponseList = new ArrayList<>();
        for (StatsDto stats : statsList) {
            ListStatsResponse statsResponse = new ListStatsResponse();
            statsResponse.setId(stats.getMatchStatsId());
            Season season = seasonRepository.findById(Long.valueOf(stats.getSeasonId())).orElseThrow();
            statsResponse.setSeasonYear(String.valueOf(season.getYear()));
            Match match = matchRepository.findById(Math.toIntExact(Long.valueOf(stats.getMatchId()))).orElseThrow();
            statsResponse.setTeam1(match.getTeam1().getTeamLogoUrl());
            statsResponse.setTeam2(match.getTeam2().getTeamLogoUrl());
            statsResponse.setMatchDate(String.valueOf(match.getMatchDate()));
            Player player = playerRepository.findById(Long.valueOf(stats.getPlayerId())).orElseThrow();
            statsResponse.setPlayer(player.getPlayerImgUrl());
            statsResponseList.add(statsResponse);
        }
        return statsResponseList;
    }

    public List<StatsPerMatchResponse> mapToStatsPerMatchResponseList(List<Object[]> result) {
        List<StatsPerMatchResponse> statsResponseList = new ArrayList<>();
        for (Object[] row : result) {
            StatsPerMatchResponse response = new StatsPerMatchResponse();
            response.setMatchId((Integer) row[2]);
            Season season = seasonRepository.findById(Long.valueOf((Integer) row[1])).orElseThrow();
            response.setSeasonYear(String.valueOf(season.getYear()));
            Match match = matchRepository.findById(Math.toIntExact(Long.valueOf((Integer) row[2]))).orElseThrow();
            response.setTeam1(match.getTeam1().getTeamLogoUrl());
            response.setTeam2(match.getTeam2().getTeamLogoUrl());
            response.setMatchDate(match.getMatchDate().toString());
            Player player = playerRepository.findById(Long.valueOf((Integer) row[3])).orElseThrow();
            response.setPlayer(player.getPlayerImgUrl());
            response.setRunsScored((Integer) row[4]);
            response.setBallFaced((Integer) row[5]);
            response.setFours((Integer) row[6]);
            response.setSixes((Integer) row[7]);
            response.setStrikeRate((Double) row[8]);
            response.setOvers((Double) row[9]);
            response.setTotalWickets((Integer) row[10]);
            response.setBowledLbw((Integer) row[11]);
            response.setOtherDismissal((Integer) row[12]);
            response.setDots((Integer) row[13]);
            response.setMaiden((Integer) row[14]);
            response.setEconomyRate((Double) row[15]);
            response.setCatchTaken((Integer) row[16]);
            response.setStumping((Integer) row[17]);
            response.setDirectRunout((Integer) row[18]);
            response.setInDirectRunout((Integer) row[19]);
            response.setIsImpactPlayer((Boolean) row[20]);
            response.setTotalPointNewSystem((Integer) row[21]);
            response.setTotalPointOldSystem((Integer) row[22]);
            statsResponseList.add(response);
        }
        return statsResponseList;
    }

}