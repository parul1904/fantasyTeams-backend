package in.fantasy.teams.controller;

import in.fantasy.teams.dto.*;
import in.fantasy.teams.response.ListStatsResponse;
import in.fantasy.teams.response.StatsPerMatchResponse;
import in.fantasy.teams.response.StatsPerPlayerResponse;
import in.fantasy.teams.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {

    private final StatsService statsService;

    @PostMapping("/add-stats")
    public ResponseEntity<StatsDto> createStats(@RequestBody StatsDto statsDto) {
        return new ResponseEntity<>(statsService.createStats(statsDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatsDto> getStatsById(@PathVariable Integer id) {
        return ResponseEntity.ok(statsService.getStatsById(id));
    }

    @GetMapping
    public ResponseEntity<List<ListStatsResponse>> getAllStats() {
        List<ListStatsResponse> stats = statsService.getAllStats();
        return ResponseEntity.ok(stats);
    }


    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<StatsPerMatchResponse>> getAllStatsByMatchId(@PathVariable Integer matchId) {
        List<StatsPerMatchResponse> stats = statsService.getAllStatsByMatchId(matchId);
        return ResponseEntity.ok(stats);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatsDto> updateStats(@PathVariable Integer id, @RequestBody StatsDto statsDto) {
        return ResponseEntity.ok(statsService.updateStats(id, statsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStats(@PathVariable Integer id) {
        statsService.deleteStats(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<StatsPerPlayerResponse> getPlayerStatsByPlayerId(@PathVariable Integer playerId) {
        StatsPerPlayerResponse stats = statsService.getPlayerStatsByPlayerId(playerId);
        return ResponseEntity.ok(stats);
    }
} 