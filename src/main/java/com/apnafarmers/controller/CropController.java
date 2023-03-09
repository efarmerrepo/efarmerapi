package com.apnafarmers.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.apnafarmers.dto.CropDto;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropType;
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

	@GetMapping("/crops")
	public ResponseEntity<GenericResponse> getCropNameByCategory(
			@RequestParam(value = "cropCategory", required = true) Long cropCategory) {

		Set<Crop> cropNameByCategory = cropService.getCropNameByCategory(cropCategory);
		List<CropDto> crops = new ArrayList<>();
		for (Crop crop : cropNameByCategory) {
			CropDto cropDto = new CropDto();
			cropDto.setId(crop.getId());
			cropDto.setName(crop.getName());
			crops.add(cropDto);
		}
		return new ResponseEntity<>(GenericResponse.builder().crops(crops).build(), HttpStatus.OK);
	}

	@RequestMapping(value = { "/crops" })
	public ResponseEntity<GenericResponse> getCropByParemeters(
			@RequestParam(value = "stateId", required = false) String stateId,
			@RequestParam(value = "districtId", required = false) String districtId,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "limit", required = false) String limit) {

		Map<String, String> querryParam = new HashMap<>();
//		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
//		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
//		querryParam.put(ApnaFarmersConstants.SORT, sort);
//		querryParam.put(ApnaFarmersConstants.LIMIT, limit);
//		cropService.getCropByParemeters(querryParam);

		return new ResponseEntity<>(GenericResponse.builder().crops(null).build(), HttpStatus.OK);

	}

}
