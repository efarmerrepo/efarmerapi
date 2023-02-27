package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import com.apnafarmers.entity.Buyer;

public interface BuyerService {

	Buyer saveBuyer(Buyer buyer);

	List<Buyer> findAll();

	List<Buyer> findByFirstNameStartsWithIgnoreCase(String startWith);

	Optional<Buyer> findById(long id);

	void deleteAll();

	void deleteById(long id);

}
