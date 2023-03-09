package com.apnafarmers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.CropType;

@Repository
public interface CropTypeRepository extends JpaRepository<CropType, Long>{

	Optional<CropType> findByName(String name);

}
