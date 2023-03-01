package com.apnafarmers.service;

import java.util.List;

import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropCategory;

public interface CropService {

	Crop saveCrop(Crop crop, Long farmerId);
	
	List<CropCategory> getCropCategories();

}
