package com.apnafarmers.service;

import java.util.List;
import java.util.Map;

import com.apnafarmers.dto.BuyerRequest;
import com.apnafarmers.dto.BuyerResponse;
import com.apnafarmers.entity.Buyer;

public interface BuyerService {

	public Buyer saveBuyer(BuyerRequest buyer);

	public List<BuyerResponse> getBuyer(Map<String, String> querryParam);

	public Buyer updateBuyer(BuyerRequest request);

}
