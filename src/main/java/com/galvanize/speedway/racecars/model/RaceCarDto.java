package com.galvanize.speedway.racecars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceCarDto {
    String nickname;
    String model;
    String year;
    int owner;
    String status;
    int topspeed;
    String type;
}

