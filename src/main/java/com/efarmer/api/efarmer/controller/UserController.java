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
import org.springframework.web.reactive.result.method.annotation.ResponseEntityResultHandler;

import com.efarmer.api.efarmer.entity.Category;
import com.efarmer.api.efarmer.entity.UserEntity;
import com.efarmer.api.efarmer.model.Categories;
import com.efarmer.api.efarmer.model.Categories.CategoriesBuilder;
import com.efarmer.api.efarmer.service.CategoryService;
import com.efarmer.api.efarmer.service.UserService;

@RequestMapping("/")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id ) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("swapnil");
		userEntity.setLastName("domde");
		userEntity.setId(id);
		Optional<UserEntity> findUserById = userService.findUserById(id);
		UserEntity user = findUserById.orElse(userEntity);
		return new ResponseEntity<UserEntity>(user , HttpStatus.OK);
				
		
	}
	
	@PostMapping(value = "/user")
	public ResponseEntity<UserEntity>  saveUser(@RequestBody UserEntity userEntity){
		
		UserEntity savedEntity = userService.save(userEntity);
		return new ResponseEntity<UserEntity>(savedEntity , HttpStatus.OK);
		
		
	}
	
	@GetMapping(value = "/category")
	public ResponseEntity<Categories>  getCategory(){
		
		List<Category> categoriesList = categoryService.findAll();
		 Categories categories = Categories.builder().categories(categoriesList).build();
		return new ResponseEntity<Categories>(categories,HttpStatus.OK);
		
		
	}
	
	

}
