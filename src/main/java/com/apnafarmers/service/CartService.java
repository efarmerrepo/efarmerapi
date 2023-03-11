package com.apnafarmers.service;

import java.util.List;

import com.apnafarmers.dto.CartRequest;
import com.apnafarmers.entity.Cart;
import com.apnafarmers.entity.Crop;

public interface CartService {

	public Cart saveCropInCart(CartRequest request);

	public Cart addCropInCart(CartRequest request);

	public List<Crop> getCartCropForUser(long userId);

	public Cart updateCropInCart(CartRequest request);

	public void deleteUserFromCart(long userId);

	public void deleteCropFromCart(long cropId);
}
