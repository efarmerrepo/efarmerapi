package com.apnafarmers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.BuyerRequest;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.Buyer;
import com.apnafarmers.service.BuyerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/buyers")
public class BuyerController {

	@Autowired
	BuyerService buyerService;

	@PostMapping("/details")
	public ResponseEntity<GenericResponse> addBuyer(@RequestBody BuyerRequest request) {
		log.info("{}", request);

		Buyer saveBuyer = buyerService.saveBuyer(request);

		return new ResponseEntity<>(GenericResponse.builder()
				.message("Buyer added successfully")
				.buyerId(saveBuyer.getId()).build(), HttpStatus.CREATED);
	}

}
