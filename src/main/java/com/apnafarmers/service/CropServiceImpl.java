package com.apnafarmers.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropType;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.exception.DataNotFoundException;
import com.apnafarmers.repository.CropTypeRepository;
import com.apnafarmers.repository.CropRepository;
import com.apnafarmers.repository.FarmerRepository;
import com.apnafarmers.utils.ApnaFarmersConstants;

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

	@Autowired
	LocationService locationService;

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

	@Override
	public void getCropByParemeters(Map<String, String> querryParam) {

		String stateId = querryParam.get(ApnaFarmersConstants.STATE_ID);
		String districtId = querryParam.get(ApnaFarmersConstants.DISTRICT_ID);
		String cityId = querryParam.get(ApnaFarmersConstants.CITY_ID);
		String cropCategory = querryParam.get(ApnaFarmersConstants.CROP_CATEGORY);
		String cropId = querryParam.get(ApnaFarmersConstants.CROP_ID);
		String quality = querryParam.get(ApnaFarmersConstants.QUALITY);
		String cropType = querryParam.get(ApnaFarmersConstants.CROP_TYPE);
		String pinCode = querryParam.get(ApnaFarmersConstants.PINCODE);
		String avilabilityFromDate = querryParam.get(ApnaFarmersConstants.AVAILABILITY_FROM_DATE);
		String avilabilityToDate = querryParam.get(ApnaFarmersConstants.AVAILABILITY_TO_DATE);
		String limitString = querryParam.get(ApnaFarmersConstants.LIMIT);
		String offset = querryParam.get(ApnaFarmersConstants.OFFSET);

		List<Crop> crops = null;

		if (StringUtils.isNotEmpty(stateId)) {
			String state = locationService.findStateById(Long.valueOf(stateId));

		} else if (StringUtils.isNotEmpty(districtId)) {

		} else if (StringUtils.isNotEmpty(cityId)) {

		} else if (StringUtils.isNotEmpty(cropCategory)) {

		} else if (StringUtils.isNotEmpty(cropId)) {

		} else if (StringUtils.isNotEmpty(quality)) {

		} else if (StringUtils.isNotEmpty(cropType)) {

		} else if (StringUtils.isNotEmpty(pinCode)) {

		} else if (StringUtils.isNotEmpty(avilabilityFromDate)) {

		} else if (StringUtils.isNotEmpty(avilabilityToDate)) {

		} else if (StringUtils.isNotEmpty(avilabilityFromDate) && StringUtils.isNotEmpty(avilabilityToDate)) {

		} else if (StringUtils.isNotEmpty(limitString) && StringUtils.isNotEmpty(offset)) {

		}

	}

}
