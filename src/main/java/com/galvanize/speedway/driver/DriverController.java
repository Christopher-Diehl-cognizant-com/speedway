package com.galvanize.speedway.driver;

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
	public ResponseEntity getDrivers(){
		return new ResponseEntity(this.driverService.getDrivers(), HttpStatus.OK);
	}

	@PostMapping("/drivers")
	@ResponseStatus(HttpStatus.CREATED)
	public void addDrivers(@RequestBody Driver driver)
	{
		driverService.addDrivers(driver);
	}
}
