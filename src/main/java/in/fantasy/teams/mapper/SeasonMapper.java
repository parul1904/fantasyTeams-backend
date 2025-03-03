package in.fantasy.teams.mapper;

import in.fantasy.teams.dto.SeasonDto;
import in.fantasy.teams.entity.Season;

public class SeasonMapper {

    public static SeasonDto mapToSeasonDto(Season season) {
        return new SeasonDto(
                season.getSeasonId(),
                season.getYear()
        );
    }

    public static Season mapToSeason(SeasonDto seasonDto) {
        return new Season(
                seasonDto.getSeasonId(),
                seasonDto.getYear()
        );
    }
}