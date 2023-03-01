package com.apnafarmers.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.City;
import com.apnafarmers.entity.Country;
import com.apnafarmers.entity.District;
import com.apnafarmers.entity.State;
import com.apnafarmers.service.LocationService;
import com.apnafarmers.utils.ApnaFarmersConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	LocationService locationService;

	@GetMapping("/countries")
	public ResponseEntity<GenericResponse> getAllCountries() {
		log.info("Inside get All countries ");
		List<Country> findAllCountries = locationService.findAllCountries();
		GenericResponse response = GenericResponse.builder().countries(findAllCountries).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/states")
	public ResponseEntity<GenericResponse> getState(
			@RequestParam(value = "countryId", required = false) String countryId) {
		log.info("Inside get All states");

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.COUNTRY_ID, countryId);
		List<State> stateList = locationService.findAllStates(querryParam);
		GenericResponse response = GenericResponse.builder().states(stateList).build();

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	
	@GetMapping("/districts")
	public ResponseEntity<GenericResponse> getDistricts(
			@RequestParam(value = "stateId", required = false) String stateId) {
		log.info("Inside get All Districts");

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.STATE_ID, stateId);
		List<District> districtList = locationService.findAllDistricts(querryParam);
		GenericResponse response = GenericResponse.builder().districts(districtList).build();

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/cities")
	public ResponseEntity<GenericResponse> getCities(
			@RequestParam(value = "districtId", required = false) String districtId) {
		log.info("Inside get All Cities By District");

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.DISTRICT_ID, districtId);
		List<City> cityList = locationService.findAllCities(querryParam);
		GenericResponse response = GenericResponse.builder().cities(cityList).build();

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}