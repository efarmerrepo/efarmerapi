package com.apnafarmers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.CropType;
import com.apnafarmers.service.CropService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/farmers")
public class CropTypeController {

	@Autowired
	private CropService cropService;

	@PostMapping("/crops/croptype")
	public ResponseEntity<GenericResponse> addCropType(@RequestBody CropType cropType) {
		log.info("Inside adding Crop Type");
		cropService.saveCropType(cropType);
		return new ResponseEntity<>(GenericResponse.builder().message("Success").build(), HttpStatus.CREATED);
	}

	@GetMapping("/crops/categories")
	public ResponseEntity<GenericResponse> getCropCategories() {

		List<CropType> cropCategories = cropService.getCropCategories();

		return new ResponseEntity<>(GenericResponse.builder().categories(cropCategories).build(), HttpStatus.OK);
	}
}
