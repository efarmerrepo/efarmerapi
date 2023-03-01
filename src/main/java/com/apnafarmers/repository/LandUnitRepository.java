package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.LandUnit;

@Repository
public interface LandUnitRepository extends JpaRepository<LandUnit, Long>{

}
