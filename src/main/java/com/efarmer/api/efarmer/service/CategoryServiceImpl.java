package com.efarmer.api.efarmer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.efarmer.api.efarmer.entity.Category;
import com.efarmer.api.efarmer.repository.CategoryRepository;
import com.efarmer.api.efarmer.repository.UserRepository;

public class CategoryServiceImpl  implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}
