package com.galvanize.speedway.races;

import com.galvanize.speedway.driver.Driver;
import com.galvanize.speedway.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/speedway")
public class RaceController {

    @Autowired
    RaceService raceService;

    @GetMapping("/races")
    public ResponseEntity getRaces(){
        return new ResponseEntity(this.raceService.getRaces(), HttpStatus.OK);
    }

    @PostMapping("/races")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRaces(@RequestBody Race race)
    {
        raceService.addRaces(race);
    }
}
