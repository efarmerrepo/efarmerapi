package com.apnafarmers.service;

import java.util.Optional;

import com.apnafarmers.dto.AppConfig;
import com.apnafarmers.entity.Farmer;

public interface FarmerService {

	public AppConfig getAndroidAppConfig();

	public Farmer saveFarmer(Farmer farmer);

	public Optional<Farmer> findById(long id);

}
