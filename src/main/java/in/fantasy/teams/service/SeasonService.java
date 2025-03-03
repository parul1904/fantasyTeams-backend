package in.fantasy.teams.service;

import in.fantasy.teams.dto.SeasonDto;

import java.util.List;

public interface SeasonService {
    List<SeasonDto> getAllSeasons();


    SeasonDto getSeasonById(Long seasonId);

}