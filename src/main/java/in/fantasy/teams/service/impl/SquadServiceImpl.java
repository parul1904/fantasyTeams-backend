package in.fantasy.teams.service.impl;

import in.fantasy.teams.dto.SquadDto;
import in.fantasy.teams.response.SquadResponse;
import in.fantasy.teams.response.SquadTeamResponse;
import in.fantasy.teams.entity.Squad;
import in.fantasy.teams.exception.ResourceNotFoundException;
import in.fantasy.teams.mapper.SquadMapper;
import in.fantasy.teams.repository.SquadRepository;
import in.fantasy.teams.service.SquadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SquadServiceImpl implements SquadService {

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    private SquadMapper squadMapper;

    @Override
    public List<SquadResponse> getAllSquads() {
        List<Squad> squads = squadRepository.findAll();
        List<SquadResponse> squadDtos = squads.stream()
                .map((squad) -> squadMapper.mapToSquadResponse(squad))
                .collect(Collectors.toList());
        return squadDtos;
    }

    @Override
    public SquadDto createSquad(SquadDto squadDto) {
        Squad squad = squadMapper.mapToSquad(squadDto);
        Squad savedSquad = squadRepository.save(squad);
        return squadMapper.mapToSquadDto(savedSquad);
    }

    @Override
    public SquadDto getSquadById(Long squadId) {
        Squad squad = squadRepository.findById(squadId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Squad does not exist with id: " + squadId));
        SquadDto squadDto = squadMapper.mapToSquadDto(squad);
        return squadDto;
    }

    @Override
    public SquadDto updateSquad(Long squadId, SquadDto squadDto) {
        if (!squadRepository.existsById(squadId)) {
            throw new RuntimeException("Squad not found with id: " + squadId);
        }
        Squad existingSquad = squadRepository.findById(squadId)
                .orElseThrow();
        Squad updatedSquad = squadMapper.mapToSquad(squadDto);
        updatedSquad.setSquadId(existingSquad.getSquadId());
        Squad savedSquad = squadRepository.save(updatedSquad);
        return squadMapper.mapToSquadDto(savedSquad);
    }

    @Override
    public void deleteSquad(Long squadId) {
        Squad existingSquad = squadRepository.findById(squadId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Squad does not exist with id: " + squadId));
        squadRepository.deleteById(squadId);
    }

    @Override
    public SquadTeamResponse getSquadDetailsByTeam(Long teamId) {
        List<Object[]> squadDetails = squadRepository.findSquadDetailsByTeam(Math.toIntExact(teamId));
       return squadMapper.mapToSquadTeamResponse(squadDetails);
    }
}