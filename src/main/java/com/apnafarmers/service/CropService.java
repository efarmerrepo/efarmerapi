package com.apnafarmers.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.apnafarmers.dto.CropRequest;
import com.apnafarmers.dto.CropResponse;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropCategory;

public interface CropService {

	Crop saveCrop(Crop crop, Long farmerId);
	
	List<CropCategory> getCropCategories();

	CropCategory saveCropCategory(CropCategory cropType);

	Set<Crop> getCropNameByCategory(Long cropCategory);

	List<CropResponse>  getCropByParemeters(Map<String, String> querryParam);
	
	Crop saveCrop(CropRequest crop);

	Crop updateCrop(CropRequest request);

	void deleteCropCategories(long cropId);

}
