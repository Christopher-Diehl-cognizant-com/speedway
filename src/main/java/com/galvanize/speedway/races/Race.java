package com.galvanize.speedway.races;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galvanize.speedway.driver.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RACE_TABLE")
public class Race {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String name;
    private String category;
    private String date;
    private String bestTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private Driver winner;
    @ManyToMany
    @JoinTable(name="race_participants", joinColumns=
    @JoinColumn(name="RACE_TABLE", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="DRIVER_TABLE", referencedColumnName="id")
    )
    private List<Driver> participants;


}
