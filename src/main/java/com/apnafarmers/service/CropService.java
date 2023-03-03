package com.apnafarmers.service;

import java.util.List;
import java.util.Set;

import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropType;

public interface CropService {

	Crop saveCrop(Crop crop, Long farmerId);
	
	List<CropType> getCropCategories();

	CropType saveCropType(CropType cropType);

	Set<Crop> getCropNameByCategory(Long cropCategory);

}
