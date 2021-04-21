package com.galvanize.speedway.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;

	public List<Driver> getDrivers(){
		return this.driverRepository.findAll();
	}
}
