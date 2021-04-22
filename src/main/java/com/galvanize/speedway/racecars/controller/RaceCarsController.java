package com.galvanize.speedway.racecars.controller;

import com.galvanize.speedway.racecars.model.RaceCarDto;
import com.galvanize.speedway.racecars.service.RaceCarsService;
import com.galvanize.speedway.response.SpeedwayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speedway")
public class RaceCarsController {

    @Autowired
    RaceCarsService raceCarsService;

    @GetMapping("/racecars")
    public SpeedwayResponse<RaceCarDto> getRaceCars() { return raceCarsService.getAllRaceCars(); }

    @PostMapping("/racecars")
    @ResponseStatus(HttpStatus.CREATED)
    public SpeedwayResponse<RaceCarDto> addRaceCars(@RequestBody RaceCarDto raceCarDto) {
        return raceCarsService.addRaceCar(raceCarDto);
    }
}
