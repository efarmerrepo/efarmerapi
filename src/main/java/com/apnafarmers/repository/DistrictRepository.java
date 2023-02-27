package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.District;
import com.apnafarmers.entity.State;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

	List<District> findByNameStartsWithIgnoreCase(String startWith);

//	@Query("FROM Country n where n.name = ?1")
	List<District> findByNameStartsWithIgnoreCaseOrderByName(String rating, Pageable pg);

	State findByNameIgnoreCase(String name);
	
}
