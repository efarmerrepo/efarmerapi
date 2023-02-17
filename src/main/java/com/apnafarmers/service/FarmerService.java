package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import com.apnafarmers.entity.Farmer;
import com.apnafarmers.entity.Media;

public interface FarmerService {

	Farmer saveFarmer(Farmer farmer);

	List<Farmer> findAll();

	List<Farmer> findByFirstNameStartsWithIgnoreCase(String startWith);

	Optional<Farmer> findById(long id);

	Media saveMedia(Media media);

}
