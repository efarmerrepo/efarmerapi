package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.RateUnit;

@Repository
public interface RateUnitRepository extends JpaRepository<RateUnit, Long> {

}
