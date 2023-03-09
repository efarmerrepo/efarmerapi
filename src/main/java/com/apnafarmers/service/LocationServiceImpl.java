package com.apnafarmers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.City;
import com.apnafarmers.entity.Country;
import com.apnafarmers.entity.District;
import com.apnafarmers.entity.State;
import com.apnafarmers.exception.ResourceNotFoundException;
import com.apnafarmers.repository.CountryRepository;
import com.apnafarmers.repository.DistrictRepository;
import com.apnafarmers.repository.StateRepository;
import com.apnafarmers.utils.ApnaFarmersConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Override
	public List<Country> findAllCountries() {
		log.info("Inside findAllCountries Start");
		List<Country> countries = new ArrayList<>();
		countries = countryRepository.findAll();
		log.info("Inside findAllCountries Ends");
		return countries;
	}

	@Override
	public List<State> findAllStates(Map<String, String> querryParam) {
		log.info("Inside findAllStates Start");
		List<State> states = null;
		String countryId = querryParam.get(ApnaFarmersConstants.COUNTRY_ID);

		Optional<Country> findById = countryRepository.findById(Long.valueOf(countryId));
		Country country = findById
				.orElseThrow(() -> new ResourceNotFoundException("No State Found with countryId = " + countryId));

		states = country.getStates();
		log.info("Inside findAllStates Ends ");

		return states;
	}

	@Override
	public List<District> findAllDistricts(Map<String, String> querryParam) {
		log.info("Inside findAllDistricts Start");

		List<District> districts = null;
		String stateId = querryParam.get(ApnaFarmersConstants.STATE_ID);

		Optional<State> findById = stateRepository.findById(Long.valueOf(stateId));
		State state = findById
				.orElseThrow(() -> new ResourceNotFoundException("No District Found with stateId = " + stateId));

		districts = state.getDistricts();
		log.info("Inside findAllDistricts Ends ");

		return districts;
	}

	@Override
	public List<City> findAllCities(Map<String, String> querryParam) {
		log.info("Inside findAllCities ");
		List<City> cities = null;
		String districtId = querryParam.get(ApnaFarmersConstants.DISTRICT_ID);

		Optional<District> findById = districtRepository.findById(Long.valueOf(districtId));
		District district = findById
				.orElseThrow(() -> new ResourceNotFoundException("No District Found with stateId = " + districtId));

		cities = district.getCities();

		return cities;
	}

	@Override
	public String findStateById(long id) {
		Optional<State> findById = stateRepository.findById(id);
		State state = findById.orElseThrow(() -> new ResourceNotFoundException("No State Found with stateId = " + id));

		return state.getName();

	}

}
