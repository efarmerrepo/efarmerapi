package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apnafarmers.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByNameStartsWithIgnoreCase(String startWith);

	List<Category> findByNameStartsWithIgnoreCaseOrderByName(String rating, Pageable pg);

	Category findByNameIgnoreCase(String name);

}
