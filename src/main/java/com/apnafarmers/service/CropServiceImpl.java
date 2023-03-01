package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropCategory;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.repository.CropCategoryRepository;
import com.apnafarmers.repository.CropRepository;
import com.apnafarmers.repository.FarmerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CropServiceImpl implements CropService{
	
	@Autowired
	CropRepository cropRepository;
	
	@Autowired
	FarmerRepository farmerRepository;
	
	@Autowired
	CropCategoryRepository cropCategoryRepository; 

	@Override
	public Crop saveCrop(Crop crop, Long farmerId) {
		Optional<Farmer> findById = farmerRepository.findById(farmerId);
		crop.setFarmer(findById.orElse(null));
		log.info("Saving the crop {}", crop);
		return cropRepository.save(crop);

	}

	@Override
	public List<CropCategory> getCropCategories() {
		return cropCategoryRepository.findAll();
	}

	
}
