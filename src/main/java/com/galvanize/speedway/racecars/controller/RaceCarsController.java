package com.galvanize.speedway.racecars.controller;

import com.galvanize.speedway.racecars.model.RaceCarDto;
import com.galvanize.speedway.racecars.service.RaceCarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("racecars")
public class RaceCarsController {

    @Autowired
    RaceCarsService raceCarsService;

    @GetMapping
    public List<RaceCarDto> getRaceCars() { return raceCarsService.getAllRaceCars(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RaceCarDto addRaceCars(@RequestBody RaceCarDto raceCarDto) {
        raceCarsService.addRaceCar(raceCarDto);
        return raceCarDto;
    }
}
