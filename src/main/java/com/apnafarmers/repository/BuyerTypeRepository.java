package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.BuyerType;

@Repository
public interface BuyerTypeRepository extends JpaRepository<BuyerType, Long> {

}
