package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

	List<Buyer> findByFirstNameStartsWithIgnoreCase(String startWith);

	List<Buyer> findByFirstNameStartsWithIgnoreCaseOrderByFirstName(String rating, Pageable pg);

	Buyer findByFirstNameIgnoreCase(String name);

}
