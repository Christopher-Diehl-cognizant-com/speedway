package com.galvanize.speedway.racecars.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaceCarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String nickname;
    String model;
    String year;
    int owner;
    String status;
    int topspeed;
    String type;

    public RaceCarEntity(String nickname, String model, String year, int owner, String status, int topspeed, String type) {
        this.nickname = nickname;
        this.model = model;
        this.year = year;
        this.owner = owner;
        this.status = status;
        this.topspeed = topspeed;
        this.type = type;
    }
}
