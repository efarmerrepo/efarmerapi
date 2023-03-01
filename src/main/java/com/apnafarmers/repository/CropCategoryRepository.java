package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.CropCategory;

@Repository
public interface CropCategoryRepository extends JpaRepository<CropCategory, Long>{

}
