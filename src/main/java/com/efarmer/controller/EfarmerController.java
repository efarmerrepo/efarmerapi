package com.efarmer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/efarmer")
public class EfarmerController {
	
	@RequestMapping("/helloSwap")
	public String print() {
		
		return "helloSwapnil";

	}

}
