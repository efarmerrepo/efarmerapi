package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.CropQuality;

@Repository
public interface CropQualityRepository extends JpaRepository<CropQuality, Long> {

}
