package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.AppConfig;
import com.apnafarmers.entity.AndroidAppConfig;
import com.apnafarmers.entity.BuyerType;
import com.apnafarmers.entity.CropType;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.entity.LandUnit;
import com.apnafarmers.entity.WeightUnit;
import com.apnafarmers.repository.AndroidAppConfigRepository;
import com.apnafarmers.repository.BuyerTypeRepository;
import com.apnafarmers.repository.CropTypeRepository;
import com.apnafarmers.repository.FarmerRepository;
import com.apnafarmers.repository.LandUnitRepository;
import com.apnafarmers.repository.WeightUnitRepository;

@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	private BuyerTypeRepository buyerTypeRepository;

	@Autowired
	private AndroidAppConfigRepository androidAppConfigRepository;

	@Autowired
	private LandUnitRepository landUnitRepository;

	@Autowired
	private WeightUnitRepository weightUnitRepository;

	@Autowired
	private CropTypeRepository cropTypeRepository;

	@Autowired
	private FarmerRepository farmerRepository;

	@Override
	public AppConfig getAndroidAppConfig() {
		// @formatter:off
		List<AndroidAppConfig> androidAppConfigList = androidAppConfigRepository.findAll();
		List<BuyerType> buyerTypeList = buyerTypeRepository.findAll();
		List<LandUnit> landUnitList = landUnitRepository.findAll();
		List<WeightUnit> weightUnitList = weightUnitRepository.findAll();
		List<CropType> cropCategoryList = cropTypeRepository.findAll();
		AndroidAppConfig androidAppConfig = null;
		if(androidAppConfigList.size() > 0) {
			 androidAppConfig = androidAppConfigList.get(0);
		}
		
		AppConfig build = AppConfig.builder()
		.androidAppConfig(androidAppConfig)
		.buyerType(buyerTypeList)
		.landUnit(landUnitList)
		.weightUnit(weightUnitList)
		.cropCategories(cropCategoryList)
		.build();
		// @formatter:on
		return build;
	}

	@Override
	public Farmer saveFarmer(Farmer farmer) {
		Farmer save = farmerRepository.save(farmer);
		return save;
	}

	@Override
	public Optional<Farmer> findById(long id) {
		return farmerRepository.findById(id);
	}

}
