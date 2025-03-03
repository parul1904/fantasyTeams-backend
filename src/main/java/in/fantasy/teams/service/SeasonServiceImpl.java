package in.fantasy.teams.service;

import in.fantasy.teams.dto.SeasonDto;
import in.fantasy.teams.entity.Season;
import in.fantasy.teams.exception.ResourceNotFoundException;
import in.fantasy.teams.mapper.SeasonMapper;
import in.fantasy.teams.repository.SeasonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private SeasonRepository seasonRepository;

    @Override
    public List<SeasonDto> getAllSeasons() {
        List<Season> seasons = seasonRepository.findAll();
        List<SeasonDto> seasonDtos = seasons.stream()
                .map((season) -> SeasonMapper.mapToSeasonDto(season))
                .collect(Collectors.toList());
        return seasonDtos;
    }

    @Override
    public SeasonDto getSeasonById(Long seasonId) {
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Season does not exist with id: " + seasonId));
        SeasonDto seasonDto = SeasonMapper.mapToSeasonDto(season);
        return seasonDto;
    }

}