package com.apnafarmers.service;

import java.util.List;
import java.util.Map;

import com.apnafarmers.entity.Crop;

public interface CropService {

	List<Crop> findAllCrops(Map<String, String> querryParam);

	Crop findCropById(Long id, Map<String, String> querryParam);


}
