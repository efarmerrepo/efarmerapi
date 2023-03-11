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

import com.apnafarmers.dto.CartRequest;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping("/cart")
	public ResponseEntity<GenericResponse> addCropInCart(@RequestBody CartRequest request) {

		log.info("Add user to Cart {} ", request);
		cartService.addCropInCart(request);
		return new ResponseEntity<>(
				GenericResponse.builder().message("Crop added successfully").status("Success").build(),
				HttpStatus.CREATED);
	}

	@GetMapping("/cart")
	public ResponseEntity<List<Crop>> getCartCropForUser(@RequestParam(value = "userId", required = true) Long userId) {

		log.info("User Id {}", userId );
		List<Crop> cartCropForUser = cartService.getCartCropForUser(userId);
		return new ResponseEntity<>(cartCropForUser, HttpStatus.OK);

	}

	@PutMapping("/cart")
	public ResponseEntity<GenericResponse> UpdateCart(@RequestBody CartRequest request) {

		cartService.updateCropInCart(request);
		return new ResponseEntity<>(
				GenericResponse.builder().message("Cart Updated successfully").status("Success").build(),
				HttpStatus.CREATED);

	}

	@DeleteMapping("/cart")
	public ResponseEntity<GenericResponse> deleteUserFromCart(
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "cropId", required = false) Long cropId) {

		if (userId != null) {
			cartService.deleteUserFromCart(userId);
		} else if (cropId != null) {
			cartService.deleteCropFromCart(cropId);
		}

		return new ResponseEntity<>(
				GenericResponse.builder().message("Cart Deleted successfully").status("Success").build(),
				HttpStatus.OK);
	}

}
