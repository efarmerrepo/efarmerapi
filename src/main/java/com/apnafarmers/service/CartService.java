package com.apnafarmers.service;

import java.util.List;
import java.util.Map;

import com.apnafarmers.dto.CartRequest;
import com.apnafarmers.dto.CropResponse;
import com.apnafarmers.entity.Cart;

public interface CartService {

	public Cart saveCropInCart(CartRequest request);

	public Cart addCropInCart(CartRequest request);

	public List<CropResponse> getCartCropForUser(long userId);

	public Cart updateCropInCart(CartRequest request);

	public void deleteCart(Map<String, Long> querryParam);
}
