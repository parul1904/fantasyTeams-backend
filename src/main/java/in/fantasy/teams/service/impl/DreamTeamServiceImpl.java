package in.fantasy.teams.service.impl;

import in.fantasy.teams.dto.Dropdown;
import in.fantasy.teams.mapper.DreamTeamMapper;
import in.fantasy.teams.repository.*;
import in.fantasy.teams.response.DreamTeamResponse;
import in.fantasy.teams.service.DreamTeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DreamTeamServiceImpl implements DreamTeamService {

    private final StatsRepository statsRepository;

    private final DreamTeamMapper dreamTeamMapper;

    public DreamTeamServiceImpl(StatsRepository statsRepository, DreamTeamMapper dreamTeamMapper) {
        this.statsRepository = statsRepository;
        this.dreamTeamMapper = dreamTeamMapper;
    }


    public List<DreamTeamResponse> getDreamTeamByMatchNo(Integer matchNo) {
        List<Object[]> dbResults = statsRepository.getDreamTeamByMatchNo(Long.valueOf(matchNo)).stream()
                .collect(Collectors.toList());
        return dreamTeamMapper.mapToDreamTeamResponse(dbResults);
    }
}
