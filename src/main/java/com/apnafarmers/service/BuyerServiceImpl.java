package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.Buyer;
import com.apnafarmers.repository.BuyerRepository;

@Service
public class BuyerServiceImpl implements BuyerService {
	
	@Autowired
	BuyerRepository buyerRepository; 

	@Override
	public Buyer saveBuyer(Buyer buyer) {
		return buyerRepository.save(buyer);
	}

	@Override
	public List<Buyer> findAll() {
		return null;
	}

	@Override
	public List<Buyer> findByFirstNameStartsWithIgnoreCase(String startWith) {
		return null;
	}

	@Override
	public Optional<Buyer> findById(long id) {
		return Optional.empty();
	}

	@Override
	public void deleteAll() {

	}

	@Override
	public void deleteById(long id) {

	}

}
