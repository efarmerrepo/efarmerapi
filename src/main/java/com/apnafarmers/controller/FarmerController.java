package com.apnafarmers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.AppConfig;
import com.apnafarmers.dto.FarmerRequest;
import com.apnafarmers.dto.FarmerResponse;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.service.FarmerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/farmers")
public class FarmerController {

	@Autowired
	FarmerService farmerService;

	@GetMapping("/config")
	public ResponseEntity<AppConfig> getAppConfig() {
		log.info("Inside getAppConfig");

		AppConfig androidAppConfig = farmerService.getAndroidAppConfig();
		return new ResponseEntity<>(androidAppConfig, HttpStatus.OK);
	}

	@GetMapping("/profile")
	public ResponseEntity<FarmerResponse> getFarmerById(
			@RequestParam(value = "farmerId", required = false) long farmerId) {

		FarmerResponse farmerResponse = farmerService.findById(farmerId);
		return new ResponseEntity<>(farmerResponse, HttpStatus.OK);
	}

	@PostMapping("/profile")
	public ResponseEntity<GenericResponse> addFarmer(@RequestBody FarmerRequest request) {
		log.info("{}", request);
		Farmer save = farmerService.saveFarmer(request);
		return new ResponseEntity<>(
				GenericResponse.builder().message("Farmer added successfully").farmerId(save.getId()).build(),
				HttpStatus.CREATED);
	}

	@PutMapping("/profile")
	public ResponseEntity<GenericResponse> updateFarmer(@RequestBody FarmerRequest request) {
		log.info("{}", request);
		Farmer save = farmerService.updateFarmer(request);
		return new ResponseEntity<>(
				GenericResponse.builder().message("Farmer added successfully").farmerId(save.getId()).build(),
				HttpStatus.CREATED);
	}

}
