package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apnafarmers.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

	List<Buyer> findByFirstNameStartsWithIgnoreCase(String startWith);

	List<Buyer> findByFirstNameStartsWithIgnoreCaseOrderByFirstName(String rating, Pageable pg);

	Buyer findByFirstNameIgnoreCase(String name);

}
