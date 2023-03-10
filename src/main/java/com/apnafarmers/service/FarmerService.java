package com.apnafarmers.service;

import com.apnafarmers.dto.AppConfig;
import com.apnafarmers.dto.FarmerRequest;
import com.apnafarmers.dto.FarmerResponse;
import com.apnafarmers.entity.Farmer;

public interface FarmerService {

	public AppConfig getAndroidAppConfig();

	public Farmer saveFarmer(FarmerRequest farmer);

	public FarmerResponse findById(long id);

}
