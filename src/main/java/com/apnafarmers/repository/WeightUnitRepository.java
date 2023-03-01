package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.WeightUnit;

@Repository
public interface WeightUnitRepository extends JpaRepository<WeightUnit, Long>{

}
