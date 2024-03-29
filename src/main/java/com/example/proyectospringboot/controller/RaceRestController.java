package com.example.proyectospringboot.controller;

import com.example.proyectospringboot.entity.Race;
import com.example.proyectospringboot.service.RaceService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RaceRestController {
    private final RaceService raceService;

    public RaceRestController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/races")
    public ResponseEntity<List<Race>> getAllRaces(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size,
                                                  @RequestParam(defaultValue = "name") String sortBy,
                                                  @RequestParam(defaultValue = "ASC") String sortDirection)
    {
        Page<Race> racePage = raceService.getAllRacesPaged(page, size, sortBy, sortDirection);
        return ResponseEntity.ok().body(racePage.stream().toList());
    }

    @GetMapping("/races/{round}")
    public ResponseEntity<Race> getByCode(@PathVariable int round) {
        return this.raceService.getRaceByRound(round)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/races")
    public ResponseEntity<Race> create(@RequestBody Race race){
        if (race.getRaceId() != null) {
            return ResponseEntity.badRequest().build();
        }
        raceService.saveRace(race);
        return ResponseEntity.ok(race);
    }

    @PutMapping("/races")
    public ResponseEntity<Race> update(@RequestBody Race race){
        raceService.saveRace(race);
        return ResponseEntity.ok(race);
    }

    @Transactional
    @DeleteMapping("/races/{round}")
    public ResponseEntity<Race> deleteByCode(@PathVariable int round){
        raceService.deleteRaceByRound(round);
        return ResponseEntity.noContent().build();
    }
}
