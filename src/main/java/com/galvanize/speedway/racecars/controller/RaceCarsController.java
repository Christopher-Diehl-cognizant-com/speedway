package com.galvanize.speedway.racecars.controller;

import com.galvanize.speedway.racecars.model.RaceCarDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("racecars")
public class RaceCarsController {

    List<RaceCarDto> raceCars;

    RaceCarsController() {
        raceCars = new ArrayList<>();
    }
    @GetMapping
    public List<RaceCarDto> getRaceCars() { return raceCars; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RaceCarDto addRaceCars(@RequestBody RaceCarDto raceCarDto) {
        raceCars.add(raceCarDto);
        return raceCarDto;
    }
}
