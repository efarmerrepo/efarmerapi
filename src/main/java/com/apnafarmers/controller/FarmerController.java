package com.apnafarmers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.AppConfig;
import com.apnafarmers.dto.CropDto;
import com.apnafarmers.dto.FarmerDto;
import com.apnafarmers.dto.FarmerDto.FarmerDtoBuilder;
import com.apnafarmers.dto.MediaDto;
import com.apnafarmers.entity.AndroidAppConfig;
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
		
		AndroidAppConfig androidAppConfig =  farmerService.getAndroidAppConfig();
		AppConfig build = AppConfig.builder().androidAppConfig(null).buyerType(null).landUnit(null).build();
		return new ResponseEntity<>(build, HttpStatus.OK);
	}

//	@GetMapping("/details")
//	public ResponseEntity<List<FarmerDto>> getAllFarmers(@RequestParam(required = false) String name) {
//		List<Farmer> farmers = new ArrayList<Farmer>();
//
//		if (name == null)
//			farmerService.findAll().forEach(farmers::add);
//		else
//			farmerService.findByFirstNameStartsWithIgnoreCase(name).forEach(farmers::add);
//
//		if (farmers.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//
//		List<FarmerDto> farmerDtoList = new ArrayList<>();
//		for (Farmer farmer : farmers) {
//
//			FarmerDto response = FarmerDto.builder().farmerId(farmer.getId()).fName(farmer.getFirstName())
//					.lName(farmer.getLastName()).land(farmer.getLand()).landUnit(farmer.getLandUnit())
//					.city(farmer.getCity()).district(farmer.getDistrict()).pinCode(farmer.getPincode()).build();
//
//			List<MediaDto> mediaModelList = new ArrayList<>();
//			Set<Media> medias = farmer.getMedias();
//			for (Media media : medias) {
//				mediaModelList.add(MediaDto.builder().type(media.getType()).url(media.getUrl()).build());
//			}
//			response.setMedia(mediaModelList);
//
//			List<CropDto> cropDtoList = new ArrayList<>();
//			Set<Crop> crops = farmer.getCrops();
//			for (Crop crop : crops) {
//				cropDtoList.add(CropDto.builder().id(crop.getId()).cropTypeId(crop.getCropTypeId())
//						.cropType(crop.getCropType()).cropName(crop.getName()).rate(crop.getRate())
//						.quantity(crop.getQuantity()).quantityUnit(crop.getQuantityUnit()).land(crop.getLand())
//						.landUnit(crop.getLandUnit()).city(crop.getCity()).district(crop.getDistrict())
//						.pinCode(crop.getPinCode()).media(mediaModelList).build());
//
//			}
//			response.setCrops(cropDtoList);
//
//			farmerDtoList.add(response);
//		}
//
//		return new ResponseEntity<>(farmerDtoList, HttpStatus.OK);
//	}

	@GetMapping("/details")
	public ResponseEntity<FarmerDto> getFarmerById(@RequestParam(value = "farmerId", required = false) long farmerId) {
		Farmer farmer = farmerService.findById(farmerId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Farmer with id = " + farmerId));

		log.info("{}", farmer);
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
					.cropTypeId(crop.getCropTypeId())
					.cropType(crop.getCropType())
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
	public ResponseEntity<Farmer> addFarmer(@RequestBody FarmerDto request) {
		log.info("{}", request);
		List<MediaDto> mediaModelList = request.getMedia();

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

		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

//	@PutMapping("/details/{id}")
//	public ResponseEntity<Farmer> updateFarmer(@PathVariable("id") long id, @RequestBody FarmerDto request) {
//		Farmer farmer = farmerService.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
//
//		List<MediaDto> mediaModelList = request.getMedia();
//
//		farmer.setFirstName(request.getFName());
//		farmer.setLastName(request.getLName());
//		farmer.setLand(request.getLand());
//		farmer.setLandUnit(request.getLandUnit());
//		farmer.setCity(request.getCity());
//		farmer.setDistrict(request.getDistrict());
//		farmer.setPincode(request.getPinCode());
//
//		MediaDto mediaModel = mediaModelList.get(0);
//		Media media = new Media();
//		media.setType(mediaModel.getType());
//		media.setUrl(mediaModel.getUrl());
//		farmer.addMedia(media);
//
//		List<CropDto> cropDtoList = request.getCrops();
//		if (cropDtoList != null) {
//			for (CropDto cropDto : cropDtoList) {
//				Crop crop = new Crop();
//				crop.setCropTypeId(cropDto.getCropTypeId());
//				crop.setCropType(cropDto.getCropType());
//				crop.setName(cropDto.getCropName());
//				crop.setRate(cropDto.getRate());
//				crop.setQuantity(cropDto.getQuantity());
//				crop.setQuantityUnit(cropDto.getQuantityUnit());
//				crop.setLand(cropDto.getLand());
//				crop.setLandUnit(crop.getLandUnit());
//				crop.setCity(cropDto.getCity());
//				crop.setDistrict(cropDto.getDistrict());
//				crop.setPinCode(cropDto.getPinCode());
//				farmer.addCrop(crop);
//			}
//		}
//
//		log.info("Saving Farmer {} ", farmer);
//
//		Farmer save = farmerService.saveFarmer(farmer);
//
//		return new ResponseEntity<>(farmerService.saveFarmer(save), HttpStatus.OK);
//	}

	@DeleteMapping("/details/{id}")
	public ResponseEntity<HttpStatus> deleteFarmer(@PathVariable("id") long id) {
		farmerService.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/details")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		farmerService.deleteAll();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
