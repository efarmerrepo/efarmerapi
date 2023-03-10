package com.apnafarmers.controller;

import java.util.HashMap;
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

import com.apnafarmers.dto.BuyerRequest;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.Buyer;
import com.apnafarmers.service.BuyerService;
import com.apnafarmers.utils.ApnaFarmersConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/buyers")
public class BuyerController {

	@Autowired
	BuyerService buyerService;

	@PostMapping("/profile")
	public ResponseEntity<GenericResponse> addBuyer(@RequestBody BuyerRequest request) {
		log.info("{}", request);

		Buyer saveBuyer = buyerService.saveBuyer(request);

		return new ResponseEntity<>(
				GenericResponse.builder().message("Buyer added successfully").buyerId(saveBuyer.getId()).build(),
				HttpStatus.CREATED);
	}

	@GetMapping("/profile")
	public ResponseEntity<GenericResponse> getBuyer(
			@RequestParam(value = "cropCategoryId", required = false) String cropCategoryId,
			@RequestParam(value = "buyerId", required = false) String buyerId,
			@RequestParam(value = "cropId", required = false) String cropId) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.CROP_CATEGORY_ID, cropCategoryId);
		querryParam.put(ApnaFarmersConstants.BUYER_ID, buyerId);
		querryParam.put(ApnaFarmersConstants.CITY_ID, cropId);

		return new ResponseEntity<>(
				GenericResponse.builder().message("Success").buyers(buyerService.getBuyer(querryParam)).build(),
				HttpStatus.CREATED);
	}

}
