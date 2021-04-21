package com.galvanize.speedway.racecars.service;

import com.galvanize.speedway.racecars.model.RaceCarDto;
import com.galvanize.speedway.racecars.model.RaceCarEntity;
import com.galvanize.speedway.racecars.repository.RaceCarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceCarsService {

    @Autowired
    private RaceCarsRepository raceCarsRepository;

    public void addRaceCar(RaceCarDto raceCarDto) {
        raceCarsRepository.save(new RaceCarEntity(
                raceCarDto.getNickname(),
                raceCarDto.getModel(),
                raceCarDto.getYear(),
                raceCarDto.getOwner(),
                raceCarDto.getStatus(),
                raceCarDto.getTopspeed(),
                raceCarDto.getType()
        ));
    }

    public List<RaceCarDto> getAllRaceCars() {
        return raceCarsRepository.findAll()
                .stream()
                .map(raceCarEntity -> new RaceCarDto(
                        raceCarEntity.getNickname(),
                        raceCarEntity.getModel(),
                        raceCarEntity.getYear(),
                        raceCarEntity.getOwner(),
                        raceCarEntity.getStatus(),
                        raceCarEntity.getTopspeed(),
                        raceCarEntity.getType()
                ))
                .collect(Collectors.toList());
    }
}
