package com.apnafarmers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.HomePageResponse;
import com.apnafarmers.service.HomePageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeController {

	@Autowired
	HomePageService homePageService;

	@GetMapping("/home")
	public ResponseEntity<HomePageResponse> getHomePageDetails() {
		log.info("Inside getHomePageDetails");
		HomePageResponse homePageResponse = homePageService.getHomePageResponse();

		return new ResponseEntity<>(homePageResponse, HttpStatus.OK);
	}

}
