package com.efarmer.api.efarmer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efarmer.api.efarmer.entity.Category;


public interface CategoryRepository  extends JpaRepository<Category, Long> {

}