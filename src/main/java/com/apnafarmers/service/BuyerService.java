package com.apnafarmers.service;

import java.util.List;
import java.util.Map;

import com.apnafarmers.dto.BuyerRequest;
import com.apnafarmers.entity.Buyer;

public interface BuyerService {

	public Buyer saveBuyer(BuyerRequest buyer);

	public List<Buyer> getBuyer(Map<String, String> querryParam);

}
