package com.galvanize.speedway.races;

import com.galvanize.speedway.driver.Driver;
import com.galvanize.speedway.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    public List<Race> getRaces(){
        return this.raceRepository.findAll();
    }

    public void addRaces(Race race)
    {
        this.raceRepository.save(race);
    }
}
