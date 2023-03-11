package com.apnafarmers.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.apnafarmers.entity.Crop;
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

		List<CropResponse> cropByParemeters = cropService.getCropByParemeters(querryParam);

		return new ResponseEntity<>(GenericResponse.builder().crops(cropByParemeters).build(), HttpStatus.OK);

	}

	@DeleteMapping("/crops")
	public ResponseEntity<GenericResponse> deleteCropById() {
		return new ResponseEntity<>(GenericResponse.builder().status("Success").build(), HttpStatus.OK);
	}
}