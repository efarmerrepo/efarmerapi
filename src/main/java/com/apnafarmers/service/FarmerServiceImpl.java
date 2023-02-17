package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apnafarmers.entity.Farmer;
import com.apnafarmers.entity.Media;
import com.apnafarmers.repository.FarmerRepository;
import com.apnafarmers.repository.MediaRepository;

@Component
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	FarmerRepository farmerRepository;
	
	@Autowired
	MediaRepository mediaRepository;
	

	@Override
	public Farmer saveFarmer(Farmer farmer) {
		Farmer save = farmerRepository.save(farmer);
		return save;
	}
	
	@Override
	public List<Farmer> findAll() {
		List<Farmer> farmers = farmerRepository.findAll();
		return farmers;
	}
	
	@Override
	public List<Farmer> findByFirstNameStartsWithIgnoreCase(String startWith) {
		List<Farmer> farmers = farmerRepository.findByFirstNameStartsWithIgnoreCase(startWith);
		return farmers;
	}

	@Override
	public Optional<Farmer> findById(long id) {
		return farmerRepository.findById(id);
	}
	
	@Override
	public Media saveMedia(Media media) {
		Media save = mediaRepository.save(media);
		return save;
	}
	

}
