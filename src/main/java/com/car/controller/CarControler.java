package com.car.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarControler {

	@GetMapping("getCar")
	public String getCarData() {
		return "Fetching data from CAR API by uisng restTemplate !!!";

	}

}
