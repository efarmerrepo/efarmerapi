package com.apnafarmers.controller;

import java.util.List;

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

import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.CropCategory;
import com.apnafarmers.service.CropService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/farmers/crops")
public class CropCateogyController {

	@Autowired
	private CropService cropService;

	@PostMapping("/cropCategory")
	public ResponseEntity<GenericResponse> addCropCategory(@RequestBody CropCategory cropCategory) {
		log.info("Inside adding Crop Crop Category");
		cropService.saveCropCategory(cropCategory);
		return new ResponseEntity<>(GenericResponse.builder().message("Success").build(), HttpStatus.CREATED);
	}

	@GetMapping("/categories")
	public ResponseEntity<GenericResponse> getCropCategories() {

		List<CropCategory> cropCategories = cropService.getCropCategories();

		return new ResponseEntity<>(GenericResponse.builder().categories(cropCategories).build(), HttpStatus.OK);
	}

	@PutMapping("/categories")
	public ResponseEntity<GenericResponse> updateCropCategories(@RequestBody CropCategory cropCategory) {

		log.info("Inside adding Crop Crop Category");
		cropService.saveCropCategory(cropCategory);
		return new ResponseEntity<>(GenericResponse.builder().message("Success").build(), HttpStatus.CREATED);
	}

	@DeleteMapping("/categories")
	public ResponseEntity<GenericResponse> deleteCropCategories(
			@RequestParam(value = "cropCategoryId", required = true) long cropCategoryId) {

		cropService.deleteCropCategories(cropCategoryId);

		return new ResponseEntity<>(GenericResponse.builder().message("Success").build(), HttpStatus.OK);
	}
}
