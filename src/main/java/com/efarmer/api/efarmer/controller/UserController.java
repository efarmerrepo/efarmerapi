package com.efarmer.api.efarmer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efarmer.api.efarmer.entity.Category;
import com.efarmer.api.efarmer.entity.UserEntity;
import com.efarmer.api.efarmer.model.Categories;
import com.efarmer.api.efarmer.service.CategoryService;
import com.efarmer.api.efarmer.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Operation(summary = "Get a User by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the User", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("swapnil");
		userEntity.setLastName("domde");
		userEntity.setId(id);
		Optional<UserEntity> findUserById = userService.findUserById(id);
		UserEntity user = findUserById.orElse(userEntity);
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);

	}

	@Operation(summary = "Save User")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Saved the user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
	@PostMapping(value = "/user")
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity) {

		UserEntity savedEntity = userService.save(userEntity);
		return new ResponseEntity<UserEntity>(savedEntity, HttpStatus.OK);

	}

	@Operation(summary = "Get List of Categories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "category fetched", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@GetMapping(value = "/category")
	public ResponseEntity<Categories> getCategory() {

		List<Category> categoriesList = categoryService.findAll();
		Categories categories = Categories.builder().categories(categoriesList).build();
		return new ResponseEntity<Categories>(categories, HttpStatus.OK);

	}

}
