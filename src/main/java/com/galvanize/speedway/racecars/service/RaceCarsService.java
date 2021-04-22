package com.galvanize.speedway.racecars.service;

import com.galvanize.speedway.racecars.model.RaceCarDto;
import com.galvanize.speedway.racecars.model.RaceCarEntity;
import com.galvanize.speedway.racecars.repository.RaceCarsRepository;
import com.galvanize.speedway.response.SpeedwayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceCarsService {

    @Autowired
    private RaceCarsRepository raceCarsRepository;

    public SpeedwayResponse<RaceCarDto> addRaceCar(RaceCarDto raceCarDto) {
        raceCarsRepository.save(new RaceCarEntity(
                raceCarDto.getNickname(),
                raceCarDto.getModel(),
                raceCarDto.getYear(),
                raceCarDto.getOwner(),
                raceCarDto.getStatus(),
                raceCarDto.getTopspeed(),
                raceCarDto.getType()
        ));
        return new SpeedwayResponse<>(
                HttpStatus.CREATED,
                HttpStatus.CREATED.value(),
                List.of(raceCarDto)
        );
    }

    public SpeedwayResponse<RaceCarDto> getAllRaceCars() {
        List<RaceCarDto> racecars = raceCarsRepository.findAll()
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
        return new SpeedwayResponse<>(
                HttpStatus.OK,
                HttpStatus.OK.value(),
                racecars
        );
    }
}
