package com.galvanize.speedway.driver;

import com.galvanize.speedway.response.SpeedwayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/speedway")
public class DriverController {
	@Autowired
	DriverService driverService;

	@GetMapping("/drivers")
	public SpeedwayResponse<Driver> getDrivers(){
		return this.driverService.getDrivers();
	}

	@PostMapping("/drivers")
	@ResponseStatus(HttpStatus.CREATED)
	public SpeedwayResponse<Driver> addDrivers(@RequestBody Driver driver)
	{
		return driverService.addDrivers(driver);
	}
}
