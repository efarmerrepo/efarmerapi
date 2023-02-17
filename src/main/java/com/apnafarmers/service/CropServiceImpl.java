package com.apnafarmers.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.Crop;
import com.apnafarmers.repository.CropRepository;

@Service
public class CropServiceImpl implements CropService {

	@Autowired
	CropRepository cropRepository;

	@Override
	public List<Crop> findAllCrops(Map<String, String> querryParam) {
		return null;
	}

	@Override
	public Crop findCropById(Long id, Map<String, String> querryParam) {
		Optional<Crop> cropOpt = cropRepository.findById(id);

		Crop crop = null ;
		if (cropOpt.isPresent()) {
			crop = cropOpt.get();
		}

		return crop;

	}

}
