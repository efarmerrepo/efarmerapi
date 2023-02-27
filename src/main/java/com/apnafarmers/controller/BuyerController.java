package com.apnafarmers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.BuyerDto;
import com.apnafarmers.entity.Buyer;
import com.apnafarmers.entity.Location;
import com.apnafarmers.service.BuyerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/buyers")
public class BuyerController {
	
	@Autowired
	BuyerService buyerService; 
	
	@PostMapping("/details")
	public ResponseEntity<Buyer> addBuyer(@RequestBody BuyerDto request) {
		log.info("{}", request);

		Buyer buyer = new Buyer();

		buyer.setFirstName(request.getFirstName());
		buyer.setLastName(request.getLastName());
		buyer.setBuyerTypeId(request.getBuyerTypeId());
		buyer.setMobileNumber(request.getMobileNumber());
		buyer.setWhatsappNumber(request.getWhatsappNumber());
		buyer.setEmail(request.getEmail());
		
		Location location = new Location();
		location.setAddress1(request.getAddress1());
		location.setAddress2(request.getAddress2());
		location.setStateId(request.getStateId());
		location.setDistrictId(request.getDistrictId());
		location.setTehsilId(request.getTehsilId());
		location.setCity(request.getCity());
		location.setPinCode(request.getPinCode());
		location.setLatitude(request.getLatitude());
		location.setLongitude(request.getLongitude());

		buyer.setLocation(location);
		
		buyer.setCompanyName(request.getCompanyName());

		log.info("Saving Buyer {} ", buyer);

		Buyer saveBuyer = buyerService.saveBuyer(buyer);

		return new ResponseEntity<>(saveBuyer, HttpStatus.CREATED);
	}


}
