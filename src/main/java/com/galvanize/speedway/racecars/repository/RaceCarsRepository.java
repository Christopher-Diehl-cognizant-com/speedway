package com.galvanize.speedway.racecars.repository;

import com.galvanize.speedway.racecars.model.RaceCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceCarsRepository extends JpaRepository<RaceCarEntity, Long> {
}
