package com.apnafarmers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.Buyer;
import com.apnafarmers.repository.BuyerRepository;

@Service
public class BuyerServiceImpl implements BuyerService{

	@Autowired
	BuyerRepository buyerRepository; 

	@Override
	public Buyer saveBuyer(Buyer buyer) {
		return buyerRepository.save(buyer);
	}

}
