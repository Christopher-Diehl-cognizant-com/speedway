package com.galvanize.speedway.driver;

import com.galvanize.speedway.response.SpeedwayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;

	public SpeedwayResponse<Driver> getDrivers(){

		List<Driver> drivers = this.driverRepository.findAll();
		return new SpeedwayResponse<>(
				HttpStatus.OK,
				HttpStatus.OK.value(),
				drivers
		);
	}

	public SpeedwayResponse<Driver> addDrivers(Driver driver)
	{

		this.driverRepository.save(driver);
		return new SpeedwayResponse<>(
				HttpStatus.CREATED,
				HttpStatus.CREATED.value(),
				List.of(driver)
		);
	}
}
