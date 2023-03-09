package com.apnafarmers.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.CropDto;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropType;
import com.apnafarmers.entity.Farmer;
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
	public ResponseEntity<GenericResponse> addCrop(@RequestBody CropDto request,
			@RequestParam(value = "farmerId", required = true) Long farmerId) {
		log.info("Insid addCrop {}", request);
		Crop crop = new Crop();
		CropType cropType = new CropType();
		Long cropTypeId = request.getCropTypeId();
		String cropTypeName = request.getCropType();
		cropType.setId(cropTypeId);
		cropType.setName(cropTypeName);
		crop.setCropType(cropType);
		crop.setName(request.getName());
		crop.setRate(request.getRate());
		crop.setQuantity(request.getQuantity());
		crop.setQuantityUnit(request.getQuantityUnit());
		crop.setLand(request.getLand());
		crop.setLandUnit(request.getLandUnit());
		crop.setCity(request.getCity());
		crop.setDistrict(request.getDistrict());
		crop.setPinCode(request.getPinCode());

		Crop save = cropService.saveCrop(crop, farmerId);

		return new ResponseEntity<>(
				GenericResponse.builder().status("Crop added successfully").cropId(save.getId()).build(),
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

		List<Crop> crops = cropService.getCropByParemeters(querryParam);

		List<CropDto> cropDtoList = new ArrayList<>();
		for (Crop crop : crops) {
			CropDto cropDto = new CropDto();
			cropDto.setId(crop.getId());

			Farmer farmer = crop.getFarmer();
			cropDto.setFirstName(farmer.getFirstName());
			cropDto.setLastName(farmer.getLastName());
			CropType type = crop.getCropType();
			cropDto.setCropTypeId(type.getId());
			cropDto.setCropType(type.getName());
			cropDto.setCropName(crop.getName());
			cropDto.setRate(crop.getRate());
			cropDto.setQuantity(crop.getQuantity());
			cropDto.setQuantityUnit(crop.getQuantityUnit());
			cropDto.setLand(crop.getLand());
			cropDto.setLandUnit(crop.getLandUnit());
			cropDto.setCity(crop.getCity());
			cropDto.setDistrict(crop.getDistrict());
			cropDto.setPinCode(pinCode);
			cropDto.setMedia(null);
			cropDtoList.add(cropDto);
		}

		return new ResponseEntity<>(GenericResponse.builder().crops(cropDtoList).build(), HttpStatus.OK);

	}

}