package com.apnafarmers.service;

import com.apnafarmers.dto.BuyerRequest;
import com.apnafarmers.entity.Buyer;

public interface BuyerService {

	public Buyer saveBuyer(BuyerRequest buyer);

}
