package in.fantasy.teams.mapper;

import in.fantasy.teams.dto.SquadDto;
import in.fantasy.teams.dto.SquadResponse;
import in.fantasy.teams.dto.SquadTeamResponse;
import in.fantasy.teams.entity.Player;
import in.fantasy.teams.entity.Season;
import in.fantasy.teams.entity.Squad;
import in.fantasy.teams.entity.Team;
import in.fantasy.teams.repository.PlayerRepository;
import in.fantasy.teams.repository.SeasonRepository;
import in.fantasy.teams.repository.TeamRepository;
import in.fantasy.teams.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SquadMapper {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public SquadDto mapToSquadDto(Squad squad) {
        SquadDto squadDto = new SquadDto();
        squadDto.setSquadId(Math.toIntExact(squad.getSquadId()));
        squadDto.setSeasonId(Math.toIntExact(squad.getSeason().getSeasonId()));
        squadDto.setTeamId(Math.toIntExact(squad.getTeam().getTeamId()));
        squadDto.setPlayerId(Math.toIntExact(squad.getPlayer().getPlayerId()));
        return squadDto;
    }

    public SquadResponse mapToSquadResponse(Squad squad) {
        SquadResponse squadResponse = new SquadResponse();
        squadResponse.setSquadId(squad.getSquadId());
        if (null != squad.getSeason()) squadResponse.setSeasonYear(squad.getSeason().getYear());
        if (null != squad.getTeam()) squadResponse.setTeamLogo(squad.getTeam().getTeamLogoUrl());
        if (null != squad.getPlayer()) {
            squadResponse.setPlayerName(squad.getPlayer().getPlayerName());
            squadResponse.setPlayerImage(squad.getPlayer().getPlayerImgUrl());
        }
        return squadResponse;
    }

    public Squad mapToSquad(SquadDto squadDto) {
        Squad squad = new Squad();
        Optional<Season> season = seasonRepository.findById(Long.valueOf(squadDto.getSeasonId()));
        if (season.isPresent()) {
            squad.setSeason(season.get());
        }
        Team team = teamRepository.findById(Long.valueOf(squadDto.getTeamId().toString())).orElseThrow();
        squad.setTeam(team);
        Player player = playerRepository.findById(Long.valueOf(squadDto.getPlayerId().toString())).orElseThrow();
        squad.setPlayer(player);
        return squad;
    }

    public List<SquadTeamResponse> mapToSquadTeamResponse(List<Object[]> squadDetails) {
        List<SquadTeamResponse> squadTeamResponses = new java.util.ArrayList<>();
        Season season = seasonRepository.findById(Long.valueOf((Integer) squadDetails.get(0)[1])).orElseThrow();
        Team team = teamRepository.findById(Long.valueOf((Integer) squadDetails.get(0)[2])).orElseThrow();
        Map<String, Object> teamMap = new HashMap<>();
        teamMap.put("teamName", team.getTeamName());
        teamMap.put("teamLogo", team.getTeamLogoUrl());

        List<Map<String, Object>> playerDetailsList = new ArrayList<>();

        squadDetails.forEach(squadDetail -> {
            Map<String, Object> playerDetailsMap = new HashMap<>();
            Player player = playerRepository.findById(Long.valueOf((Integer) squadDetail[3])).orElseThrow();
            playerDetailsMap.put("playerImage", player.getPlayerImgUrl());
            playerDetailsMap.put("playerNickName", player.getNickName());
            playerDetailsMap.put("playerName", player.getPlayerName());
            playerDetailsMap.put("playerRole", player.getRole());
            playerDetailsMap.put("playerCountry", player.getCountry());
            playerDetailsList.add(playerDetailsMap);
        });

        SquadTeamResponse squadTeamResponse = new SquadTeamResponse();
        squadTeamResponse.setSeasonYear(season.getYear());
        squadTeamResponse.setTeamDetails(teamMap);
        squadTeamResponse.setPlayerDetails(playerDetailsList);
        squadTeamResponses.add(squadTeamResponse);

        return squadTeamResponses;
    }
}