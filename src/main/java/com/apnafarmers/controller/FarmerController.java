package com.apnafarmers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.AppConfig;
import com.apnafarmers.dto.CropDto;
import com.apnafarmers.dto.FarmerDto;
import com.apnafarmers.dto.FarmerDto.FarmerDtoBuilder;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.dto.MediaDto;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.entity.Location;
import com.apnafarmers.entity.Media;
import com.apnafarmers.exception.ResourceNotFoundException;
import com.apnafarmers.service.FarmerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/farmers")
public class FarmerController {

	@Autowired
	FarmerService farmerService;

	@GetMapping("/config")
	public ResponseEntity<AppConfig> getAppConfig() {
		log.info("Inside getAppConfig");

		AppConfig androidAppConfig = farmerService.getAndroidAppConfig();
		return new ResponseEntity<>(androidAppConfig, HttpStatus.OK);
	}

	@GetMapping("/details")
	public ResponseEntity<FarmerDto> getFarmerById(@RequestParam(value = "farmerId", required = false) long farmerId) {
		Farmer farmer = farmerService.findById(farmerId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Farmer with id = " + farmerId));

		log.info("GetFarmerById{}", farmer);
		//@formatter:off
		 FarmerDtoBuilder farmerBuilder = FarmerDto.builder()
				.farmerId(farmer.getId())
				.fName(farmer.getFirstName())
				.lName(farmer.getLastName())
				.land(farmer.getLand())
				.landUnit(farmer.getLandUnit());
		
		Location location = farmer.getLocation();
		
		farmerBuilder.city(location.getCity());
		farmerBuilder.districtId(location.getDistrictId());
		farmerBuilder.pinCode(location.getPinCode());
		
		log.info("{}",farmerBuilder);

		List<MediaDto> mediaModelList = new ArrayList<>();

		Set<Media> medias = farmer.getMedias();

		for (Media media : medias) {
			mediaModelList.add(MediaDto.builder().type(media.getType()).url(media.getUrl()).build());
		}
		farmerBuilder.media(mediaModelList);

		List<CropDto> cropDtoList = new ArrayList<>();
		Set<Crop> crops = farmer.getCrops();
		for (Crop crop : crops) {
			cropDtoList.add(CropDto.builder()
					.id(crop.getId())
					.cropTypeId(crop.getCropType().getId())
					.cropType(crop.getCropType().getName())
					.cropName(crop.getName())
					.rate(crop.getRate())
					.quantity(crop.getQuantity())
					.quantityUnit(crop.getQuantityUnit())
					.land(crop.getLand())
					.landUnit(crop.getLandUnit())
					.city(crop.getCity())
					.district(crop.getDistrict())
					.pinCode(crop.getPinCode())
					.media(mediaModelList).build());

		}
		farmerBuilder.crops(cropDtoList);
		
		FarmerDto response = farmerBuilder.build();
		
		log.info("{}",response);
		//@formatter:on

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/details")
	public ResponseEntity<GenericResponse> addFarmer(@RequestBody FarmerDto request) {
		log.info("{}", request);

		Farmer farmer = new Farmer();

		farmer.setProfileImage(request.getProfileImage());
		farmer.setFirstName(request.getFName());
		farmer.setLastName(request.getLName());
		farmer.setMobileNumber(request.getMobileNumber());
		farmer.setWhatsAppNumber(request.getWhatsappNumber());
		farmer.setEmail(request.getEmail());

		Location location = new Location();
		location.setLatitude(request.getLatitude());
		location.setLongitude(request.getLongitude());
		location.setAddress1(request.getAddress1());
		location.setVillage(request.getVillage());
		location.setStateId(request.getStateId());
		location.setDistrictId(request.getDistrictId());
		location.setTehsilId(request.getTehsilId());
		location.setCity(request.getCity());
		location.setPinCode(request.getPinCode());

		farmer.setLand(request.getLand());
		farmer.setLand(request.getLandUnit());

		farmer.setLocation(location);

		log.info("Saving Farmer {} ", farmer);

		Farmer save = farmerService.saveFarmer(farmer);

		return new ResponseEntity<>(
				GenericResponse.builder().message("Farmer added successfully").farmerId(save.getId()).build(),
				HttpStatus.CREATED);
	}

}
