package com.galvanize.speedway.racecars;

import com.galvanize.speedway.racecars.model.RaceCarDto;
import com.galvanize.speedway.racecars.model.RaceCarEntity;
import com.galvanize.speedway.racecars.repository.RaceCarsRepository;
import com.galvanize.speedway.racecars.service.RaceCarsService;
import com.galvanize.speedway.response.SpeedwayResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RaceCarsServiceUT {

    @Mock
    RaceCarsRepository repository;

    @InjectMocks
    RaceCarsService subject;

    @Test
    void addRaceCarTest() throws Exception {

        RaceCarDto raceCarDto1 = new RaceCarDto(
                "The Condor",
                "Corvette",
                "2019",
                27,
                "AVAILABLE",
                189,
                "compact");
        subject.addRaceCar(raceCarDto1);

        verify(repository).save(
                new RaceCarEntity(
                        "The Condor",
                        "Corvette",
                        "2019",
                        27,
                        "AVAILABLE",
                        189,
                        "compact")
        );
    }

    @Test
    void getAllRaceCarsTest() throws Exception {
        RaceCarEntity entity1 =  new RaceCarEntity(
                "The Condor",
                "Corvette",
                "2019",
                27,
                "AVAILABLE",
                189,
                "compact");

        RaceCarDto dto1 = new RaceCarDto(
                "The Condor",
                "Corvette",
                "2019",
                27,
                "AVAILABLE",
                189,
                "compact");

        when(repository.findAll()).thenReturn(
                List.of(entity1)
        );

        SpeedwayResponse<RaceCarDto> actual = subject.getAllRaceCars();
        assertThat(actual.getData()).isEqualTo(
                List.of(dto1)
        );
    }
}
