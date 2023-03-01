package com.apnafarmers.service;

import java.util.List;
import java.util.Map;

import com.apnafarmers.entity.City;
import com.apnafarmers.entity.Country;
import com.apnafarmers.entity.District;
import com.apnafarmers.entity.State;

public interface LocationService {

	public List<Country> findAllCountries();

	public List<State>findAllStates(Map<String, String> querryParam);

	public List<District> findAllDistricts(Map<String, String> querryParam);

	public List<City> findAllCities(Map<String, String> querryParam);


}
