package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropType;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.exception.DataNotFoundException;
import com.apnafarmers.repository.CropTypeRepository;
import com.apnafarmers.repository.CropRepository;
import com.apnafarmers.repository.FarmerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CropServiceImpl implements CropService {

	@Autowired
	CropRepository cropRepository;

	@Autowired
	FarmerRepository farmerRepository;

	@Autowired
	CropTypeRepository cropCategoryRepository;

	@Override
	public Crop saveCrop(Crop crop, Long farmerId) {
		Optional<Farmer> findById = farmerRepository.findById(farmerId);
		crop.setFarmer(findById.orElse(null));
		log.info("Saving the crop {}", crop);
		return cropRepository.save(crop);

	}

	@Override
	public List<CropType> getCropCategories() {
		return cropCategoryRepository.findAll();
	}

	@Override
	public CropType saveCropType(CropType cropType) {
		CropType save = cropCategoryRepository.save(cropType);
		return save;
	}

	@Override
	public Set<Crop> getCropNameByCategory(Long cropCategory) {

		Optional<CropType> findById = cropCategoryRepository.findById(cropCategory);
		CropType cropType = findById.orElseThrow(() -> new DataNotFoundException());
		Set<Crop> crops = cropType.getCrops();
		return crops;
	}

}
