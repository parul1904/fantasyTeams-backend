package in.fantasy.teams.controller;

import in.fantasy.teams.dto.StatsDto;
import in.fantasy.teams.response.DreamTeamResponse;
import in.fantasy.teams.response.ListStatsResponse;
import in.fantasy.teams.response.StatsPerMatchResponse;
import in.fantasy.teams.response.StatsPerPlayerResponse;
import in.fantasy.teams.service.DreamTeamService;
import in.fantasy.teams.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/dreamTeam")
public class DreamTeamController {

    private final DreamTeamService dreamTeamService;


    @GetMapping("/match/{matchNo}")
    public ResponseEntity<List<DreamTeamResponse>> getDreamTeamByMatchNo(@PathVariable Integer matchNo) {
        return ResponseEntity.ok(dreamTeamService.getDreamTeamByMatchNo(matchNo));
    }
}