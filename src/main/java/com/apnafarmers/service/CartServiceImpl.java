package com.apnafarmers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.CartRequest;
import com.apnafarmers.entity.Cart;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.exception.DataNotFoundException;
import com.apnafarmers.repository.CartRepository;
import com.apnafarmers.repository.CropRepository;
import com.apnafarmers.repository.UserInfoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	CropRepository cropRepository;

	@Override
	public Cart saveCropInCart(CartRequest request) {
		return null;
	}

	@Override
	public Cart addCropInCart(CartRequest request) {

		Long userId = request.getUserId();
		log.info("user Id : {}", userId);
		userInfoRepository.findById(userId)
				.orElseThrow(() -> new DataNotFoundException("User does'nt exist with the Id " + request.getUserId()));

		Long cropId = request.getCropId();
		log.info("Crop Id : {}", cropId);
		cropRepository.findById(cropId)
				.orElseThrow(() -> new DataNotFoundException("Crop Doesn't Exist with the id " + request.getCropId()));

		Cart cart = new Cart();
		cart.setCropId(request.getCropId());
		cart.setUserId(request.getUserId());
		Cart save = cartRepository.save(cart);
		return save;
	}

	@Override
	public List<Crop> getCartCropForUser(long userId) {

		List<Cart> cartList = cartRepository.findByUserId(Long.valueOf(userId))
				.orElseThrow(() -> new DataNotFoundException("User Doesn't Exist with the id " + userId));

		List<Long> cropIdList = new ArrayList<>();

		cartList.stream().forEach((r) -> cropIdList.add(r.getCropId()));

		List<Crop> cropList = cropRepository.findAllById(cropIdList);

		return cropList;

	}

	@Override
	public Cart updateCropInCart(CartRequest request) {

		Cart cart = new Cart();
		cart.setCropId(request.getCropId());
		cart.setUserId(request.getUserId());
		Cart save = cartRepository.save(cart);
		return save;

	}

	@Override
	@Transactional
	public void deleteUserFromCart(long userId) {
		cartRepository.deleteByUserId(userId);
	}

	@Override
	@Transactional
	public void deleteCropFromCart(long cropId) {
		cartRepository.deleteByCropId(cropId);

	}

}
