package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.Category;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.repository.CategoryRepository;
import com.apnafarmers.repository.CropRepository;
import com.apnafarmers.repository.FarmerRepository;

@Service
public class CropServiceImpl implements CropService {

	@Autowired
	CropRepository cropRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	FarmerRepository farmerRepository;

	@Override
	public Crop saveCrop(Crop crop, long id) {
		
		Optional<Farmer> findById = farmerRepository.findById(id);
		crop.setFarmer(findById.orElse(null));
		
		return cropRepository.save(crop);
	}
	
	@Override
	public Crop saveCrop(Crop farmer) {
		
		
		return cropRepository.save(farmer);
	}


	@Override
	public List<Crop> findAll() {
		return cropRepository.findAll();
	}

	@Override
	public List<Crop> findByNameStartsWithIgnoreCase(String startWith) {
		return cropRepository.findByNameStartsWithIgnoreCase(startWith);
	}

	@Override
	public Optional<Crop> findById(long id) {
		return cropRepository.findById(id);
	}

	@Override
	public void deleteAll() {
		cropRepository.deleteAll();
	}

	@Override
	public void deleteById(long id) {
		cropRepository.deleteById(id);
	}

	@Override
	public List<Category> getCropCategories() {
		return categoryRepository.findAll();
	}

}
