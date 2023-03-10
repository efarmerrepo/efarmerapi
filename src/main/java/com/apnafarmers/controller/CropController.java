package com.apnafarmers.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.CropRequest;
import com.apnafarmers.dto.CropResponse;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.dto.MediaDTO;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.entity.Media;
import com.apnafarmers.service.CropService;
import com.apnafarmers.utils.ApnaFarmersConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/farmers")
public class CropController {

	@Autowired
	private CropService cropService;

	@PostMapping("/crops")
	public ResponseEntity<GenericResponse> addCrop(@RequestBody CropRequest request) {
		log.info("Inside addCrop {}", request);
		Crop save = cropService.saveCrop(request);

		return new ResponseEntity<>(
				GenericResponse.builder().status("Crop added successfully").cropId(save.getId()).build(),
				HttpStatus.CREATED);
	}

	@PutMapping("/crops")
	public ResponseEntity<GenericResponse> updateCrop(@RequestBody CropRequest request) {
		log.info("Inside addCrop {}", request);
		Crop save = cropService.updateCrop(request);

		return new ResponseEntity<>(
				GenericResponse.builder().status("Crop Updated successfully").cropId(save.getId()).build(),
				HttpStatus.CREATED);
	}

	@GetMapping(value = { "/crops" })
	public ResponseEntity<GenericResponse> getCropByParemeters(
			@RequestParam(value = "stateId", required = false) String stateId,
			@RequestParam(value = "districtId", required = false) String districtId,
			@RequestParam(value = "cityId", required = false) String cityId,
			@RequestParam(value = "cropCategory", required = false) String cropCategory,
			@RequestParam(value = "cropId", required = false) String cropId,
			@RequestParam(value = "quality", required = false) String quality,
			@RequestParam(value = "cropType", required = false) String cropType,
			@RequestParam(value = "pinCode", required = false) String pinCode,
			@RequestParam(value = "avilabilityFromDate", required = false) String avilabilityFromDate,
			@RequestParam(value = "avilabilityToDate", required = false) String avilabilityToDate,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "offset", required = false) String offset) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.STATE_ID, stateId);
		querryParam.put(ApnaFarmersConstants.DISTRICT_ID, districtId);
		querryParam.put(ApnaFarmersConstants.CITY_ID, cityId);
		querryParam.put(ApnaFarmersConstants.CROP_CATEGORY, cropCategory);
		querryParam.put(ApnaFarmersConstants.CROP_ID, cropId);
		querryParam.put(ApnaFarmersConstants.QUALITY, quality);
		querryParam.put(ApnaFarmersConstants.CROP_TYPE, cropType);
		querryParam.put(ApnaFarmersConstants.PINCODE, pinCode);
		querryParam.put(ApnaFarmersConstants.AVAILABILITY_FROM_DATE, avilabilityFromDate);
		querryParam.put(ApnaFarmersConstants.AVAILABILITY_TO_DATE, avilabilityToDate);
		querryParam.put(ApnaFarmersConstants.LIMIT, limit);
		querryParam.put(ApnaFarmersConstants.OFFSET, offset);

		List<CropResponse> cropResponseList = new ArrayList<>();
		List<Crop> crops = cropService.getCropByParemeters(querryParam);

		if (StringUtils.isNotEmpty(cropCategory)) {
			for (Crop crop : crops) {

				CropResponse cropDto = new CropResponse();
				cropDto.setId(crop.getId());
				cropDto.setCropName(crop.getName());
				cropResponseList.add(cropDto);
			}
			return new ResponseEntity<>(GenericResponse.builder().crops(cropResponseList).build(), HttpStatus.OK);
		}

		for (Crop crop : crops) {
			CropResponse cropResponse = new CropResponse();
			cropResponse.setId(crop.getId());

			Farmer farmer = crop.getFarmer();
			if (farmer != null) {
				cropResponse.setFirstName(farmer.getFirstName());
				cropResponse.setLastName(farmer.getLastName());
			}

			if (crop.getCropType() != null) {
				cropResponse.setCropTypeid(crop.getCropType().getId());
				cropResponse.setCropType(crop.getCropType().getName());
			}
			
			if(crop.getCropCategory() != null) {
				cropResponse.setCropCategoryId(crop.getCropCategory().getId());
				cropResponse.setCropCategory(crop.getCropCategory().getName());
			}

			cropResponse.setCropName(crop.getName());
			cropResponse.setRate(crop.getRate());
			cropResponse.setQuantity(crop.getWeight());
			if (crop.getWeightUnit() != null) {
				cropResponse.setQuantityUnit(crop.getWeightUnit().getName());
			}
			cropResponse.setLand(crop.getLand());

			if (crop.getLandUnit() != null) {
				cropResponse.setLandUnit(crop.getLandUnit().getName());
			}

			log.info("crop.getLocation {}", crop.getLocation());
			
			if (crop.getLocation().getCity() != null) {
				cropResponse.setCity(crop.getLocation().getCity().getName());
			}

			if (crop.getLocation().getDistrict() != null) {
				cropResponse.setDistrict(crop.getLocation().getDistrict().getName());
			}

			cropResponse.setPinCode(crop.getLocation().getPinCode());

			List<MediaDTO> mediaResponse = new ArrayList<>();
			Set<Media> medList = crop.getMedias();
			if (medList != null) {
				for (Media media : medList) {
					MediaDTO mediaDto = new MediaDTO();
					mediaDto.setType(media.getUrl());
					mediaDto.setUrl(media.getType());
					mediaResponse.add(mediaDto);
				}
				cropResponse.setMedia(mediaResponse);
				cropResponseList.add(cropResponse);
			}
		}

		return new ResponseEntity<>(GenericResponse.builder().crops(cropResponseList).build(), HttpStatus.OK);

	}

	@DeleteMapping("/crops")
	public ResponseEntity<GenericResponse> deleteCropById() {
		return new ResponseEntity<>(GenericResponse.builder().status("Success").build(), HttpStatus.OK);
	}
}